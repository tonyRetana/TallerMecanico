/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author PC
 */
import java.util.Date;
public class Compras {
    //atributos
    private int idCompra;
    private double totalCompra;
    private Date fechaHora;
    private int cantidad;

    //setters
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //getters
    public int getIdCompra() {
        return idCompra;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public int getCantidad() {
        return cantidad;
    }

    //contructores
    public Compras(int idCompra, double totalCompra, Date fechaHora, int cantidad) {
        this.idCompra = idCompra;
        this.totalCompra = totalCompra;
        this.fechaHora = fechaHora;
        this.cantidad = cantidad;
    }
    public Compras() {
        idCompra = 0;
        totalCompra = 0;
        fechaHora = new Date();
        cantidad = 0;
    }
    
}
