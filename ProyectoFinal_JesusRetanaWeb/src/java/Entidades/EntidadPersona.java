/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author PC
 */
public class EntidadPersona {
    //atributos
    private int identificacion;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String correoElectronico;

    //Setters
    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    //Getters
    public int getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
    
    //constructores
    public EntidadPersona(int identificacion, String nombre, String primerApellido, String segundoApellido, String telefono, String correoElectronico) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }
    public EntidadPersona() {
        identificacion = 0;
        nombre = "";
        primerApellido = "";
        segundoApellido = "";
        telefono = "";
        correoElectronico = "";
    }
    
    //otros metodos
    public String MostrarPersona(){
        return "Nombre: "+getNombre()+" Apellidos: "+getPrimerApellido()+" "+getSegundoApellido()+" Contacto: "+getTelefono()+" | "+getCorreoElectronico();
    }
}
