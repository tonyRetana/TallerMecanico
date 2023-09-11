/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import AccesoDatos.ADVendedores;
import Entidades.EntidadVendedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class LogicaVendedor {
    //atributos
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public List<EntidadVendedor> ListarVendedores(String condicion)throws Exception{
        List<EntidadVendedor> arreglo = new ArrayList();
        ADVendedores advendedor;
        try {
            advendedor = new ADVendedores();
            arreglo = advendedor.ListarVendedores(condicion);
        } catch (Exception e) {
            throw e;
        }
        return arreglo;
    }
    
    public int InsertarVendedor(EntidadVendedor vendedor) throws Exception{
        int resultado = -1;
        try {
            ADVendedores adVendedor = new ADVendedores();
            resultado = adVendedor.InsertarVendedor(vendedor);
            _mensaje = adVendedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int ActualizarVendedor(EntidadVendedor vendedor)throws Exception{
        int resultado = -1;
        try {
            ADVendedores adVendedor = new ADVendedores();
            resultado = adVendedor.ActualizarVendedor(vendedor);
            _mensaje = adVendedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
