
package com.mycompany.bibliotecaweb.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private Connection conexion = null;
    private final String URL_MYSQL = "jdbc:mysql://localhost:3306/control_bibliotecas";
    private final String USER = "usuario";
    private final String PASSWORD = "12345";

    public Connection obtenerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            System.out.println("Conexión exitosa");
            return conexion;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al registrar el driver de MySQL: " + e);
        }
        return null;
    }

    public void desconectar() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión");
                e.printStackTrace();
            }
        }
    }
}
