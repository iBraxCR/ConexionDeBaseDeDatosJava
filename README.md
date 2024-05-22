# Proyecto de Gestión de Contactos - Java

Este es un proyecto simple de gestión de contactos en Java, utilizando una base de datos MySQL para almacenar la información de los contactos.

## Descripción

El proyecto consiste en una aplicación de consola que permite al usuario realizar las siguientes operaciones:

- Guardar un contacto nuevo.
- Consultar contactos existentes por número, nombre o cédula.
- Salir del programa.

## Requisitos

Para ejecutar este proyecto necesitas tener instalado:

- Java Development Kit (JDK) 8 o superior.
- MySQL Server.
- IDE Java (opcional, puedes usar cualquier editor de texto y la línea de comandos).

## Configuración

### Base de Datos

1. **Crear la base de datos y la tabla**:

   - Abre tu cliente MySQL (por ejemplo, MySQL Workbench o la línea de comandos).
   - Ejecuta el siguiente script SQL para crear la base de datos y la tabla necesaria:

     ```sql
     CREATE DATABASE IF NOT EXISTS Java;
     USE Java;

     CREATE TABLE IF NOT EXISTS contactos (
         id INT AUTO_INCREMENT PRIMARY KEY,
         numero INT NOT NULL,
         nombre VARCHAR(100) NOT NULL,
         correo VARCHAR(100) NOT NULL,
         cedula INT NOT NULL
     );
     ```

2. **Configurar la conexión a la base de datos**:

   - Abre el archivo `Conexion.java` y modifica las siguientes líneas con la configuración de tu base de datos:

     ```java
     private static final String HOST = "localhost";
     private static final String USER = "root";
     private static final String PASSWORD = "";
     private static final String DATABASE = "Java";
     ```

### Ejecutar la Aplicación

1. **Compilar el proyecto**:

   - Abre una terminal.
   - Navega al directorio donde se encuentra tu proyecto.
   - Ejecuta el siguiente comando para compilar el proyecto:

     ```bash
     javac Conexion.java App.java
     ```

2. **Ejecutar la aplicación**:

   - Una vez compilado, ejecuta la aplicación con el siguiente comando:

     ```bash
     java App
     ```

3. **Utilizar la aplicación**:

   - Sigue las instrucciones que aparecen en la consola y en las ventanas de diálogo (JOptionPane) para interactuar con la aplicación.
   - Puedes guardar nuevos contactos, consultar contactos existentes por número, nombre o cédula, y salir del programa cuando hayas terminado.

## Ejemplo de Uso

1. **Guardar un nuevo contacto**:

   - Selecciona la opción 1 en el menú principal.
   - Ingresa los datos solicitados (número, nombre, correo y cédula).

2. **Consultar contactos existentes**:

   - Selecciona la opción 2 en el menú principal.
   - Elige el criterio de consulta (número, nombre o cédula).
   - Ingresa el valor correspondiente para buscar contactos.

3. **Salir del programa**:

   - Selecciona la opción 3 en el menú principal.

---

¡Disfruta utilizando tu aplicación de gestión de contactos en Java!

