/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author PC
 */
public class EntidadServiciosProductos {
    //atributos
    private int codigoBarras;
    private String nombre;
    private double precio;
    private String tipo;
    private String fabricante;
    private String descripcion;

    //setter
    public void setCodigoBarras(int codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //getters
    public int getCodigoBarras() {
        return codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    //contructores
    public EntidadServiciosProductos(int codigoBarras, String nombre, double precio, String tipo, String fabricante, String descripcion) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.fabricante = fabricante;
        this.descripcion = descripcion;
    }
    public EntidadServiciosProductos(int codigoBarras, String nombre, double precio, String descripcion, String tipo) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }
    public EntidadServiciosProductos() {
        codigoBarras = 0;
        nombre = "";
        precio = 0;
        tipo = "";
        fabricante = "";
        descripcion = "";
    }
}
