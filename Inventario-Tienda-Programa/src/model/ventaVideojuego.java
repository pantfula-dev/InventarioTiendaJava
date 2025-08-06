/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.*;
/**
 *
 * @author CETECOM
 */
public class ventaVideojuego {
    private Date fechaVenta;
    private String codigoVideojuego;

    public ventaVideojuego(Date fechaVenta, String codigoVideojuego) {
        this.fechaVenta = fechaVenta;
        this.codigoVideojuego = codigoVideojuego;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getCodigoVideojuego() {
        return codigoVideojuego;
    }

    public void setCodigoVideojuego(String codigoVideojuego) {
        this.codigoVideojuego = codigoVideojuego;
    }
    
    
}

