/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.EntidadCliente;
import Entidades.EntidadInventario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 *
 * @author PC
 */
public class ADInventario {
    private Connection _cnn;
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public ADInventario() throws Exception{
        try{
            String url=Config.Config.getConnectionString();
            _cnn = DriverManager.getConnection(url); 
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public int InsertarInventario(EntidadInventario inventario)throws SQLException{
        int resultado =-1;
        ResultSet rs = null;
        try {
            String sentencia = "INSERT INTO INVENTARIO (CODIGOBARRA, CANTIDAD) VALUES (?, ?)";
            PreparedStatement pst = _cnn.prepareStatement(sentencia);

            pst.setInt(1, inventario.getCodigoBarras());
            pst.setInt(2, inventario.getCantidad());

            resultado = pst.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int ActulizarInventario(int id, int cantidad) throws SQLException {
        int resultado = -1;
        try {
            CallableStatement csp = _cnn.prepareCall("{call ActualizarInventario(?,?,?)}");
            csp.setInt(1, id);
            csp.setInt(2, cantidad);

            // Agregar este parámetro para el mensaje de salida
            csp.registerOutParameter(3, Types.VARCHAR);

            boolean bool = csp.execute();
            _mensaje = csp.getString(3);
            resultado = 1;
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    public int ActualizarInventarioPorID(int id, int cantidad, String nombre,double precio)throws SQLException{
        int resultado = -1;
        try {
            String sentencia = "update INVENTARIO set CANTIDAD=CANTIDAD+? where CODIGOBARRA=?; update SERVICIO_PRODUCTO set NOMBRE = ?, PRECIO=? where CODIGOBARRA = ?;";
            PreparedStatement pst = _cnn.prepareStatement(sentencia);

            pst.setInt(1, cantidad);
            pst.setInt(2, id);
            pst.setString(3, nombre);
            pst.setDouble(4, precio);
            pst.setInt(5, id);
            resultado = pst.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
}
