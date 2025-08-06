/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.*;

/**
 *
 * @author CETECOM
 */
public class Conexion {

    private static Connection conexion;

    public static Connection conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/db/estim.db";
                conexion = DriverManager.getConnection(url);
                System.out.println("Conexion establecida a SQLite");
            }
        } catch (SQLException e) {
            System.out.println("Hubo un error al conectar a SQLite" + e.getMessage());
        }
        return conexion;
    }
    
}
