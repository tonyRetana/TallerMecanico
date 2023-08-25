/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Date;

/**
 *
 * @author Progra
 */
public class EntidadDetalleFactura {
    private int codFactura;
    private int codBarra;
    private int cantidad;
    private Date fecha;
    private int id_Detalle;
    private double total;

    public void setCodFactura(int codFactura) {
        this.codFactura = codFactura;
    }

    public void setCodBarra(int codBarra) {
        this.codBarra = codBarra;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId_Detalle(int id_Detalle) {
        this.id_Detalle = id_Detalle;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCodFactura() {
        return codFactura;
    }

    public int getCodBarra() {
        return codBarra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getId_Detalle() {
        return id_Detalle;
    }

    public double getTotal() {
        return total;
    }

    public EntidadDetalleFactura() {
        this.codFactura = 0;
        this.codBarra = 0;
        this.cantidad = 0;
        this.fecha = new Date();
        this.id_Detalle = 0;
        this.total = 0;
    }

    public EntidadDetalleFactura(int codFactura, int codBarra, int cantidad, Date fecha, int id_Detalle, double total) {
        this.codFactura = codFactura;
        this.codBarra = codBarra;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.id_Detalle = id_Detalle;
        this.total = total;
    }

    
}
