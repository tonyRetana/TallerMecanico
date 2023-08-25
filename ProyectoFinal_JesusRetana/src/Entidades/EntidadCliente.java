/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author PC
 */
public class EntidadCliente extends EntidadPersona{
    //atributos
    private String contrasena;

    //setter
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    //getter
    public String getContrasena() {
        return contrasena;
    }

    //contructores
    public EntidadCliente(String contrasena, int identificacion, String nombre, String primerApellido, String segundoApellido, String telefono, String correoElectronico) {
        super(identificacion, nombre, primerApellido, segundoApellido, telefono, correoElectronico);
        this.contrasena = contrasena;
    }
    public EntidadCliente(String contrasena) {
        this.contrasena = contrasena;
    }
    public EntidadCliente() {
        contrasena="";
    }
    public EntidadCliente(int identificacion, String nombre, String primerApellido, String segundoApellido, String telefono, String correoElectronico) {
        super(identificacion, nombre, primerApellido, segundoApellido, telefono, correoElectronico);
    }
    //otros metodos
    @Override
    public String MostrarPersona(){
        return "Nombre: "+getNombre()+" Apellidos: "+getPrimerApellido()+" "+getSegundoApellido()+" Contacto: "+getTelefono()+" | "+getCorreoElectronico()+" Su contrasena: "+getContrasena();
    }
}
