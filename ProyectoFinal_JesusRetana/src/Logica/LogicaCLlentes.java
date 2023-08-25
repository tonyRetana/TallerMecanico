/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import AccesoDatos.ADClientes;
import AccesoDatos.ADVendedores;
import Entidades.EntidadCliente;
import Entidades.EntidadVendedor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class LogicaCLlentes {
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    public List<EntidadCliente> ListarClientes(String condicion)throws Exception{
        List<EntidadCliente> arreglo = new ArrayList();
        ADClientes adCliente;
        try {
            adCliente = new ADClientes();
            arreglo = adCliente.ListarClientes(condicion);
        } catch (Exception e) {
            throw e;
        }
        return arreglo;
    }
    
    public int InsertarCliente(EntidadCliente cliente) throws Exception{
        int resultado = -1;
        try {
            ADClientes adCliente = new ADClientes();
            resultado = adCliente.InsertarCliente(cliente);
            _mensaje = adCliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public EntidadCliente ObtenerClientePorIdentificacion(int identificacion) throws Exception {
        EntidadCliente cliente = new EntidadCliente();
        try {
            ADClientes adCliente = new ADClientes();
            cliente = adCliente.ObtenerClientePorIdentificacion(identificacion);
            
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }
    
    public int ActualizarCliente(EntidadCliente cliente)throws Exception{
        int resultado = -1;
        try {
            ADClientes adCliente = new ADClientes();
            resultado = adCliente.ActualizarCliente(cliente);
            _mensaje = adCliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
