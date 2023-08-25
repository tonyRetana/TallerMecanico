/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author PC
 */
public class EntidadVendedor extends EntidadPersona{
    //atributos
    private boolean activo;

    //setter
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    //getter
    public boolean isActivo() {
        return activo;
    }
    public boolean getActivo() {
        return activo;
    }

    //contructores
    public EntidadVendedor(boolean activo, int identificacion, String nombre, String primerApellido, String segundoApellido, String telefono, String correoElectronico) {
        super(identificacion, nombre, primerApellido, segundoApellido, telefono, correoElectronico);
        this.activo = activo;
    }
    public EntidadVendedor(int identificacion, String nombre, String primerApellido, String segundoApellido, String telefono, String correoElectronico) {
        super(identificacion, nombre, primerApellido, segundoApellido, telefono, correoElectronico);
        activo = true;
    }
    public EntidadVendedor(boolean activo) {
        this.activo = activo;
    }
    public EntidadVendedor() {
        activo = false;
    }
    
    //otros metodos
    @Override
    public String MostrarPersona(){
        if(isActivo()){
            return "Empleado Activo\nNombre: "+getNombre()+" Apellidos: "+getPrimerApellido()+" "+getSegundoApellido()+" Contacto: "+getTelefono();
        }
        else{
            return "Empleado No Activo\nNombre: "+getNombre()+" Apellidos: "+getPrimerApellido()+" "+getSegundoApellido()+" Contacto: "+getTelefono();
        }
        
    }

    
}
