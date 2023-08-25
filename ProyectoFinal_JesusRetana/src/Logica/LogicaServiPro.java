/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import AccesoDatos.ADProducto;
import AccesoDatos.ADVendedores;
import Entidades.EntidadServiciosProductos;
import Entidades.EntidadVendedor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class LogicaServiPro {
    //atributos
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public int InsertarProductoServicio(EntidadServiciosProductos serviPro) throws Exception{
        int resultado = -1;
        try {
            ADProducto accesoDatos = new ADProducto();
            resultado = accesoDatos.InsertarProductoServicio(serviPro);
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    public int EliminarProductoServicio(int codBarra) throws Exception{
        int resultado = -1;
        try {
            ADProducto accesoDatos = new ADProducto();
            resultado = accesoDatos.EliminarProductoServicio(codBarra);
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public List<EntidadServiciosProductos> ListarServiPro(String condicion)throws Exception{
        List<EntidadServiciosProductos> arreglo = new ArrayList();
        ADProducto adpro;
        try {
            adpro = new ADProducto();
            arreglo = adpro.ListarServiPro(condicion);
        } catch (Exception e) {
            throw e;
        }
        return arreglo;
    }
    
    public ResultSet ListarServiProCant(String condicion)throws Exception{
        ResultSet rs;
        ADProducto adDPR;
        try {
            adDPR = new ADProducto();
            rs = adDPR.ListarServiProCant(condicion);
        } catch (Exception e) {
            throw e;
        }
        return rs;
    }
}
