/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Videojuego;

/**
 *
 * @author CETECOM
 */
public class videojuegoDAO {
    
    private Connection conexion;

    public videojuegoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean agregarVideojuego(Videojuego videojuego) {
        String sql = "Insert into Videojuego (codigo,titulo,plataforma,precio,estado) values (?,?,?,?,?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, videojuego.getCodigo());
            stmt.setString(2, videojuego.getTitulo());
            stmt.setString(3, videojuego.getPlataforma());
            stmt.setInt(4, videojuego.getPrecio());
            stmt.setInt(5, videojuego.getEstado());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Hubo un error agregando el Videojuego" + e.getMessage());
        }
        return false;
    }

    public boolean modificarVideojuego(Videojuego videojuego) {
        String sql = "Update Videojuego set titulo = ?,plataforma = ?,precio = ?,estado = ? where codigo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, videojuego.getTitulo());
            stmt.setString(2, videojuego.getPlataforma());
            stmt.setInt(3, videojuego.getPrecio());
            stmt.setInt(4, videojuego.getEstado());
            stmt.setString(5, videojuego.getCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Hubo un error modificando el videojuego" + e.getMessage());
        }
        return false;
    }

    public boolean eliminarVideojuego(String titulo) {
        String sql = "delete from Videojuego where titulo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Hubo un error eliminando el Videojuego" + e.getMessage());
        }
        return false;
    }

    public List<Videojuego> listarVideojuego() {
        List<Videojuego> lista = new ArrayList<>();
        String sql = "Select * from Videojuego";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Videojuego(
                        rs.getString("codigo"),
                        rs.getString("titulo"),
                        rs.getString("plataforma"),
                        rs.getInt("precio"),
                        rs.getInt("estado")
                ));
            }
            return lista;

        } catch (SQLException e) {
            System.out.println("Hubo un error listando los Videojuegos" + e.getMessage());
        }
        return null;
    }
    public Videojuego buscarVideojuego(String titulo){
        String sql = "Select * from Videojuego where titulo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1,titulo);
            try (ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                return(new Videojuego(
                        rs.getString("codigo"),
                        rs.getString("titulo"),
                        rs.getString("plataforma"),
                        rs.getInt("precio"),
                        rs.getInt("estado")   
                ));
            }
            

        }} catch (SQLException e) {
            System.out.println("Hubo un error buscando el videojuego" + e.getMessage());
        }
        return null;
    }
    public Videojuego buscarVideojuegoCodigo(String codigo){
        String sql = "Select * from Videojuego where codigo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1,codigo);
            try (ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                return(new Videojuego(
                        rs.getString("codigo"),
                        rs.getString("titulo"),
                        rs.getString("plataforma"),
                        rs.getInt("precio"),
                        rs.getInt("estado")   
                ));
            }
            

        }} catch (SQLException e) {
            System.out.println("Hubo un error buscando el videojuego" + e.getMessage());
        }
        return null;
    }
    public List<Videojuego> buscarVideojuegoPlataforma(String plataforma) {
        List<Videojuego> lista = new ArrayList<>();
        String sql = "Select * from Videojuego where plataforma = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1,plataforma);
            try (ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                lista.add(new Videojuego(
                        rs.getString("codigo"),
                        rs.getString("titulo"),
                        rs.getString("plataforma"),
                        rs.getInt("precio"),
                        rs.getInt("estado")
                ));
            }
            return lista;

            }} catch (SQLException e) {
            System.out.println("Hubo un error listando los Videojuegos" + e.getMessage());
        }
        return null;
    }
}
