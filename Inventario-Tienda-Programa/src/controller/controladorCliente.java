/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.clienteDAO;
import java.util.List;
import model.Cliente;
/**
 *
 * @author CETECOM
 */
public class controladorCliente {
    private clienteDAO clienteDAO;

    public controladorCliente(clienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    
    public String registrarCliente(Cliente cliente){
        if (cliente == null){
            return "el cliente no contiene informacion";
        } if (clienteDAO.buscarCliente(cliente.getRut())!= null){
            return "el cliente ya existe";
        } if (clienteDAO.buscarCliente(cliente.getRut()) == null){
            clienteDAO.agregarCliente(cliente);
            return "El cliente fue agregado sin problemas";
            }else{
        return "hubo un error";
        }   
    }
    
    public String modificarCliente(Cliente cliente) {
         if (clienteDAO.buscarCliente(cliente.getRut())== null){
            return "el cliente no existe";
         }else{
         clienteDAO.modificarCliente(cliente);
         return "EL cliente se modificó sin problemas";
         }
    }
    public String eliminarCliente(String rut) {
       if (clienteDAO.buscarCliente(rut)== null){
            return "el cliente no existe";
       }else{
         clienteDAO.eliminarCliente(rut);
         return "EL cliente se eliminó sin problemas";
         }
    }
    public List<Cliente> listarClientes(){
        return clienteDAO.listarCliente();
    }
    public Cliente buscarCliente(String rut){
        return clienteDAO.buscarCliente(rut);
    }
}
