/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ventaDAO;
import java.util.List;
import model.Venta;

/**
 *
 * @author CETECOM
 */
public class controladorVenta {
    private ventaDAO ventaDAO;

    public controladorVenta(ventaDAO ventaDAO) {
        this.ventaDAO = ventaDAO;
    }

    public String registrarVenta(Venta venta, String codigoVideojuego) {
        if (venta == null) {
            return "La venta no contiene información.";
        }
        if (codigoVideojuego == null || codigoVideojuego.isEmpty()) {
            return "El código del videojuego es inválido.";
        }
        if (!ventaDAO.agregarVenta(venta, codigoVideojuego)) {
            return "Hubo un error al registrar la venta.";
        }
        return "La venta fue registrada con éxito.";
    }

    public List<String> listarVentas() {
        List<String> ventas = ventaDAO.listarVentas();
        if (ventas == null || ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        }
        return ventas;
    }

    public List<String> listarVentasPorRut(String rut) {
        if (rut == null || rut.isEmpty()) {
            System.out.println("El RUT proporcionado es inválido.");
            return null;
        }
        List<String> ventas = ventaDAO.listarVentasRut(rut);
        if (ventas == null || ventas.isEmpty()) {
            System.out.println("No se encontraron ventas para el RUT: " + rut);
        }
        return ventas;
    }
    
}
