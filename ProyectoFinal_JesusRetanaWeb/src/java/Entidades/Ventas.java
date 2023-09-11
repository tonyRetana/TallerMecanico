/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.Date;

/**
 *
 * @author PC
 */
public class Ventas {
    //atributos
    private int idVenta;
    private double totalVenta;
    private Date fechaHora;
    private int numeroFactura;

    //setters
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    //getters
    public int getIdVenta() {
        return idVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    //constructores
    public Ventas(int idVenta, double totalVenta, Date fechaHora,int numeroFactura) {
        this.idVenta = idVenta;
        this.totalVenta = totalVenta;
        this.fechaHora = fechaHora;
        this.numeroFactura = numeroFactura;
    }
    public Ventas() {
        idVenta = 0;
        totalVenta = 0;
        fechaHora = new Date();
        numeroFactura = 0;
    }
}
