/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import AccesoDatos.ADDetalleFactura;
import Entidades.EntidadDetalleFactura;
import java.sql.ResultSet;

/**
 *
 * @author Progra
 */
public class LogicaDetalle {
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public int InsertarDetalleFactura(EntidadDetalleFactura detalle)throws Exception{
        int resultado = -1;
        
        try {
            ADDetalleFactura accesoDatos = new ADDetalleFactura();
            resultado = accesoDatos.InsertarDetalleFactura(detalle);
        } catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    public ResultSet ListarDF(String condicion)throws Exception{
        ResultSet rs;
        ADDetalleFactura adDF;
        try {
            adDF = new ADDetalleFactura();
            rs = adDF.ListarDF(condicion);
        } catch (Exception e) {
            throw e;
        }
        return rs;
    }
    public ResultSet ObtenerDetalles(String condicion)throws Exception{
        ResultSet rs;
        ADDetalleFactura adDF;
        try {
            adDF = new ADDetalleFactura();
            rs = adDF.ObtenerDetalle(condicion);
        } catch (Exception e) {
            throw e;
        }
        return rs;
    }
    public int ActualizarDetalle(int id, double total, int cantidad, int cantidadDe) throws Exception{
        int resultado = -1;
        try {
            ADDetalleFactura acceso = new ADDetalleFactura();
            resultado = acceso.ActualizarDetalle(id, total, cantidad, cantidadDe);
            _mensaje = acceso.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int EliminarDetalle(int id) throws Exception{
        int resultado = -1;
        try {
            ADDetalleFactura acceso = new ADDetalleFactura();
            resultado = acceso.EliminarDetalle(id);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
