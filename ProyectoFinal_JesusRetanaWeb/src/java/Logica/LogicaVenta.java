/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import AccesoDatos.ADCompra;
import AccesoDatos.ADFactura;
import AccesoDatos.ADVentas;
import Entidades.Ventas;
import java.sql.ResultSet;

/**
 *
 * @author PC
 */
public class LogicaVenta {
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public int InsertarProductoServicio(Ventas venta) throws Exception{
        int resultado = -1;
        try {
            ADVentas accesoDatos = new ADVentas();
            resultado = accesoDatos.InsertarVenta(venta);
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    public ResultSet ObtenerVenta(String condicion)throws Exception{
        ResultSet rs;
        ADVentas adDV;
        try {
            adDV = new ADVentas();
            rs = adDV.ObtenerVentas(condicion);
        } catch (Exception e) {
            throw e;
        }
        return rs;
    }
}
