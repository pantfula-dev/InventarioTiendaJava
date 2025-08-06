/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.videojuegoDAO;
import java.util.List;
import model.Videojuego;

/**
 *
 * @author CETECOM
 */
public class controladorVideojuego {
    private videojuegoDAO videojuegoDAO;

    public controladorVideojuego(videojuegoDAO videojuegoDAO) {
        this.videojuegoDAO = videojuegoDAO;
    }

    
    public String registrarVideojuego(Videojuego videojuego){
    if (videojuego == null){
        return "el videojuego no contiene informacion";
    }
    if (videojuegoDAO.buscarVideojuego(videojuego.getTitulo()) != null){
        return "el videojuego ya existe";
    }
    if (videojuegoDAO.agregarVideojuego(videojuego)){
        return "El Videojuego fue agregado sin problemas";
    }
    return "hubo un error al agregar el videojuego";
    }
    
    public String modificarVideojuego(Videojuego videojuego) {

         videojuegoDAO.modificarVideojuego(videojuego);
         return "EL videojuego se modifico sin problemas";
         
    }
    
    public String eliminarVideojuego(String titulo) {
    Videojuego existente = videojuegoDAO.buscarVideojuego(titulo);
    if (existente == null) {
        return "el videojuego no existe";
    }
    if (existente.getEstado() == 1) {  // Vendido
        return "No se puede eliminar un videojuego ya vendido";
    }
    if(videojuegoDAO.eliminarVideojuego(titulo)){
        videojuegoDAO.eliminarVideojuego(titulo);
        return "El videojuego se eliminó sin problemas";
    }else{
    return "El videojuego se encuentra vendido";
    }
    
    
}
    public List<Videojuego> listarVideojuegos(){
        return videojuegoDAO.listarVideojuego();
    }
    public Videojuego buscarVideojuego(String titulo){
        return videojuegoDAO.buscarVideojuego(titulo);
    }
    public Videojuego buscarVideojuegoCodigo(String codigo){
        return videojuegoDAO.buscarVideojuegoCodigo(codigo);
    }
    public List<Videojuego> buscarVideojuegoPlataforma(String plataforma){
        return videojuegoDAO.buscarVideojuegoPlataforma(plataforma);
    } 
}
