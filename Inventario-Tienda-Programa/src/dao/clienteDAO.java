/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CETECOM
 */
public class clienteDAO {

    private Connection conexion;

    public clienteDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean agregarCliente(Cliente cliente) {
        String sql = "Insert into Cliente (rut,nombre) values (?,?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cliente.getRut());
            stmt.setString(2, cliente.getNombre());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Hubo un error agregando el cliente" + e.getMessage());
        }
        return false;
    }

    public boolean modificarCliente(Cliente cliente) {
        String sql = "Update Cliente set nombre = ? where rut = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getRut());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Hubo un error modificando el cliente" + e.getMessage());
        }
        return false;
    }

    public boolean eliminarCliente(String rut) {
        String sql = "delete from cliente where rut = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, rut);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Hubo un error eliminando el cliente" + e.getMessage());
        }
        return false;
    }

    public List<Cliente> listarCliente() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "Select * from Cliente";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Cliente(
                        rs.getString("rut"),
                        rs.getString("nombre")
                ));
            }
            return lista;

        } catch (SQLException e) {
            System.out.println("Hubo un error listando los clientes" + e.getMessage());
        }
        return null;
    }
    
    public Cliente buscarCliente(String rut){
        String sql = "Select * from Cliente where rut= ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1,rut);
            try (ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                return(new Cliente(
                        rs.getString("rut"),
                        rs.getString("nombre")
                ));
            }
            

        }} catch (SQLException e) {
            System.out.println("Hubo un error buscando el cliente" + e.getMessage());
        }
        return null;
    }
}
