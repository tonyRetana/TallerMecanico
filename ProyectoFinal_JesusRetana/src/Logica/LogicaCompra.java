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
public class LogicaCompra {
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public int InsertarCompra(Compras compra,int codigoBarras) throws Exception{
        int resultado = -1;
        try {
            ADCompra accesoDatos = new ADCompra();
            resultado = accesoDatos.InsertarCompra(compra,codigoBarras);
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public ResultSet ObtenerCompra(String condicion)throws Exception{
        ResultSet rs;
        ADCompra adDC;
        try {
            adDC = new ADCompra();
            rs = adDC.ObtenerCompras(condicion);
        } catch (Exception e) {
            throw e;
        }
        return rs;
    }
}
