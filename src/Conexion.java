import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    private static final String HOST = "localhost";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DATABASE = "Java";
    private static final String URL = "jdbc:mysql://" + HOST + "/" + DATABASE + "?useSSL=false&serverTimezone=UTC";

    private Connection connection;

    public Conexion() {
        try {
            // Cargar el controlador JDBC para MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    // Método para insertar un contacto en la base de datos
    public boolean insertarContacto(int numero, String nombre, String correo, int cedula) {
        String query = "INSERT INTO contactos (numero, nombre, correo, cedula) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, numero);
            stmt.setString(2, nombre);
            stmt.setString(3, correo);
            stmt.setInt(4, cedula);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar el contacto: " + e.getMessage());
            return false;
        }
    }

    // Método para consultar contacto por número
    public String consultarPorNumero(int numero) {
        String query = "SELECT * FROM contactos WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return "Número: " + rs.getInt("numero") + "\nNombre: " + rs.getString("nombre") + 
                       "\nCorreo: " + rs.getString("correo") + "\nCédula: " + rs.getInt("cedula");
            } else {
                return "No se encontró un contacto con ese número.";
            }
        } catch (SQLException e) {
            return "Error al consultar el contacto: " + e.getMessage();
        }
    }

    // Método para consultar contacto por nombre
    public String consultarPorNombre(String nombre) {
        String query = "SELECT * FROM contactos WHERE nombre LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();
            StringBuilder result = new StringBuilder();
            while (rs.next()) {
                result.append("Número: ").append(rs.getInt("numero")).append("\nNombre: ").append(rs.getString("nombre"))
                      .append("\nCorreo: ").append(rs.getString("correo")).append("\nCédula: ").append(rs.getInt("cedula")).append("\n\n");
            }
            if (result.length() > 0) {
                return result.toString();
            } else {
                return "No se encontraron contactos con ese nombre.";
            }
        } catch (SQLException e) {
            return "Error al consultar el contacto: " + e.getMessage();
        }
    }

    // Método para consultar contacto por cédula
    public String consultarPorCedula(int cedula) {
        String query = "SELECT * FROM contactos WHERE cedula = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cedula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return "Número: " + rs.getInt("numero") + "\nNombre: " + rs.getString("nombre") + 
                       "\nCorreo: " + rs.getString("correo") + "\nCédula: " + rs.getInt("cedula");
            } else {
                return "No se encontró un contacto con esa cédula.";
            }
        } catch (SQLException e) {
            return "Error al consultar el contacto: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        // Prueba de conexión e inserción
        Conexion conexion = new Conexion();
        if (conexion.insertarContacto(123456, "Juan Perez", "juan@example.com", 7890123)) {
            System.out.println("Contacto insertado correctamente");
        } else {
            System.err.println("Error al insertar el contacto");
        }
        conexion.closeConnection();
    }
}
