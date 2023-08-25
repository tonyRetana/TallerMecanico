/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import AccesoDatos.ADInventario;
import Entidades.EntidadInventario;

/**
 *
 * @author PC
 */
public class LogicaInventario {
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public int InsertarInventario(EntidadInventario inventario)throws Exception{
        int resultado = -1;
        
        try {
            ADInventario accesoDatos = new ADInventario();
            resultado = accesoDatos.InsertarInventario(inventario);
        } catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    public int ActualizarInventario(int id, int cantidad)throws Exception{
        int resultado =-1;
        try {
            ADInventario adIn = new ADInventario();
            resultado = adIn.ActulizarInventario(id, cantidad);
            _mensaje = adIn.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int ActualizarInventarioPorID(int id, int cantidad, String nombre,double precio)throws Exception{
        int resultado =-1;
        try {
            ADInventario adIn = new ADInventario();
            resultado = adIn.ActualizarInventarioPorID(id, cantidad,nombre,precio);
            _mensaje = adIn.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
