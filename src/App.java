import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {

        int op = 0;
        int numero;
        String nombre;
        String correo;
        int cedula;

        Conexion conexion = new Conexion();
        
        JOptionPane.showMessageDialog(null, "Bienvenido al proyecto de prueba de Base de datos");

        // Bucle que sigue ejecutándose mientras la opción no sea 3 (Salir)
        do {
            try {
                op = Integer.parseInt(JOptionPane.showInputDialog("Que desea hacer? \n 1. Guardar contacto \n 2. Consultar \n 3. Salir"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción no válida. Debe ingresar un número.");
                continue;
            }

            switch (op) {
                case 1:
                    while (true) {
                        try {
                            numero = Integer.parseInt(JOptionPane.showInputDialog("Por favor ingrese el numero:"));
                            break;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Número no válido. Por favor, ingrese un número válido.");
                        }
                    }

                    nombre = JOptionPane.showInputDialog("Digite el nombre: ");
                    correo = JOptionPane.showInputDialog("Digite el correo: ");

                    while (true) {
                        try {
                            cedula = Integer.parseInt(JOptionPane.showInputDialog("Por favor ingrese la cedula"));
                            break;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Cédula no válida. Por favor, ingrese un número válido.");
                        }
                    }

                    // Llamar al método insertarContacto para guardar el contacto en la base de datos
                    if (conexion.insertarContacto(numero, nombre, correo, cedula)) {
                        JOptionPane.showMessageDialog(null, "Contacto guardado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar el contacto.");
                    }
                    break;
                case 2:
                    int consultaOp = 0;
                    do {
                        try {
                            consultaOp = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el criterio de consulta: \n 1. Por número \n 2. Por nombre \n 3. Por cédula \n 4. Regresar al menú principal"));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Opción no válida. Debe ingresar un número.");
                            continue;
                        }

                        switch (consultaOp) {
                            case 1:
                                try {
                                    numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número:"));
                                    JOptionPane.showMessageDialog(null, conexion.consultarPorNumero(numero));
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Número no válido. Debe ingresar un número.");
                                }
                                break;
                            case 2:
                                nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
                                JOptionPane.showMessageDialog(null, conexion.consultarPorNombre(nombre));
                                break;
                            case 3:
                                try {
                                    cedula = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cédula:"));
                                    JOptionPane.showMessageDialog(null, conexion.consultarPorCedula(cedula));
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Cédula no válida. Debe ingresar un número.");
                                }
                                break;
                            case 4:
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opción no válida");
                                break;
                        }
                    } while (consultaOp != 4);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }
        } while (op != 3);  // Condición del bucle: seguir mientras la opción no sea 3 (Salir)
        
        conexion.closeConnection(); // Cerrar la conexión al finalizar
    }
}
