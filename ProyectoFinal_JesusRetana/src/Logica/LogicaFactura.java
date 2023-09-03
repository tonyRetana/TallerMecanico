/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import AccesoDatos.*;
import Entidades.*;
import java.sql.ResultSet;

/**
 *
 * @author PC
 */
public class LogicaFactura {
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    public int InsertarProductoServicio() throws Exception{
        int resultado = -1;
        try {
            ADFactura accesoDatos = new ADFactura();
            resultado = accesoDatos.InsertarFactura();
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    public int EliminarFactura(int id)throws Exception{
        int resultado = -1;
        try {
            ADFactura accesoDatos = new ADFactura();
            resultado = accesoDatos.EliminarFactura(id);
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    public int ActualizarFactura(EntidadFactura factura)throws Exception{
        int resultado = -1;
        try {
            ADFactura accesoDatos = new ADFactura();
            resultado = accesoDatos.ActualizarFactura(factura);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    public ResultSet ListarFactura(String condicion)throws Exception{
        ResultSet rs =null;
        try {
            ADFactura adFac = new ADFactura();
            rs= adFac.ListarFactura(condicion);
        } catch (Exception e) {
            throw e;
        }
        return rs;
    }
}
