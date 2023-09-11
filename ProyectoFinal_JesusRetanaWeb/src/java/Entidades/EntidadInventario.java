/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author PC
 */
public class EntidadInventario {
    //atributos
    private int codigoLote;
    private int codigoBarras;
    private int cantidad;

    //setters
    public void setCodigoLote(int codigoLote) {
        this.codigoLote = codigoLote;
    }

    public void setCodigoBarras(int codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //getters
    public int getCodigoLote() {
        return codigoLote;
    }

    public int getCodigoBarras() {
        return codigoBarras;
    }

    public int getCantidad() {
        return cantidad;
    }

    //contructores
    public EntidadInventario(int codigoLote, int codigoBarras, int cantidad) {
        this.codigoLote = codigoLote;
        this.codigoBarras = codigoBarras;
        this.cantidad = cantidad;
    }
    public EntidadInventario() {
        codigoLote = 0;
        codigoBarras = 0;
        cantidad = 0;
    }
}
