/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Venta;

/**
 *
 * @author CETECOM
 */
public class ventaDAO {
     
    private Connection conexion;

    public ventaDAO(Connection conexion) {
        this.conexion = conexion;
    }

   public boolean agregarVenta(Venta venta, String codigoVideojuego) {
    String sql = "Insert into Venta (fechaVenta,clienteRut) values (?,?)";
    String sql_venta = "Insert into VentaVideojuego (idVenta,codigoVideojuego) values (?,?)";
    
    try (PreparedStatement ventastmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ventastmt.setDate(1, new java.sql.Date(venta.getFechaVenta().getTime()));
        ventastmt.setString(2, venta.getClienteRut());
        ventastmt.executeUpdate();
        
        try (ResultSet rs = ventastmt.getGeneratedKeys()) {
            if (rs.next()) {
                int IdVenta = rs.getInt(1);
                
                try (PreparedStatement ventagamestmt = conexion.prepareStatement(sql_venta)) {
                    ventagamestmt.setInt(1, IdVenta);
                    ventagamestmt.setString(2, codigoVideojuego);
                    ventagamestmt.executeUpdate();
                    return true;
                }
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al agregar venta: " + e.getMessage());
    }
    return false;
    }

   public List<String> listarVentas() {
    List<String> ventas = new ArrayList<>();
    String sql = """
                 SELECT      v.id AS venta_id, 
                             v.clienteRut AS clienteRut, 
                             c.nombre AS cliente_nombre, 
                             a.codigo AS videojuego_codigo
                         FROM Venta v
                         JOIN VentaVideojuego va ON v.id = va.idVenta
                         JOIN Videojuego a ON va.codigoVideojuego = a.codigo
                         JOIN Cliente c ON v.clienteRut = c.rut
                 """;
    try (Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            String[] venta = {
                String.valueOf(rs.getInt("venta_id")),               // ID de la venta
                rs.getString("clienteRut"),                           // RUT del cliente
                rs.getString("cliente_nombre"),                       // Nombre del cliente
                rs.getString("videojuego_codigo")                     // Código del videojuego
            };
            // Agregar la venta formateada a la lista
            ventas.add(String.join(", ", venta));
        }
        return ventas;

    } catch (SQLException e) {
        System.out.println("Hubo un error listando las ventas: " + e.getMessage());
        return null;
    }
}
 
   
    public List<String> listarVentasRut(String rut) {
    List<String> ventas = new ArrayList<>();
    String sql = """
                 SELECT      v.id AS venta_id, 
                             v.fechaVenta, 
                             v.clienteRut AS clienteRut, 
                             c.rut AS cliente_rut, 
                             c.nombre AS cliente_nombre, 
                             a.codigo AS videojuego_codigo
                         FROM Venta v
                         JOIN VentaVideojuego va ON v.id = va.idVenta
                         JOIN Videojuego a ON va.codigoVideojuego = a.codigo
                         JOIN Cliente c ON v.clienteRut = c.rut
                         WHERE c.rut = ?
                 """;
    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setString(1, rut); // Establecer el parámetro rut
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String[] venta = {
                    String.valueOf(rs.getInt("venta_id")),               // ID de la venta
                    rs.getString("clienteRut"),                           // RUT del cliente
                    rs.getString("cliente_nombre"),                       // Nombre del cliente
                    rs.getString("videojuego_codigo")                     // Código del videojuego
                };
                // Agregar la venta formateada a la lista
                ventas.add(String.join(", ", venta));
            }
        }
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        }
        return ventas;

    } catch (SQLException e) {
        System.out.println("Hubo un error listando las ventas: " + e.getMessage());
    }
    return ventas; // Devuelve la lista de ventas, aunque esté vacía en caso de error
}

    
    
}