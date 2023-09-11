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
public class EntidadFactura {
    //atributos
    private int numeroFactura;
    private Date fechaHora;
    private double total;
    private int idVendedor;
    private int idCliente;
    private String resumen;

    //setters
    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    //getters
    public int getNumeroFactura() {
        return numeroFactura;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public double getTotal() {
        return total;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getResumen() {
        return resumen;
    }

    //contructores
    public EntidadFactura(int numeroFactura, Date fechaHora, double total, int idVendedor, int idCliente, String resumen) {
        this.numeroFactura = numeroFactura;
        this.fechaHora = fechaHora;
        this.total = total;
        this.idVendedor = idVendedor;
        this.idCliente = idCliente;
        this.resumen = resumen;
    }
    public EntidadFactura() {
        this.numeroFactura = 0;
        this.fechaHora = new Date();
        this.total = 0;
        this.idVendedor = 0;
        this.idCliente = 0;
        this.resumen = "";
    }
}
