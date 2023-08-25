/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.EntidadServiciosProductos;
import Entidades.EntidadVendedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class ADProducto {
    //atributos
    private Connection _cnn;
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public ADProducto() throws Exception{
        try{
            String url=Config.Config.getConnectionString();
            _cnn = DriverManager.getConnection(url); 
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public int InsertarProductoServicio(EntidadServiciosProductos serviPro) throws SQLException{
        int resultado = -1;
        
        try {
            CallableStatement csp = _cnn.prepareCall("{call InsertarServicioProducto(?,?,?,?,?,?,?)}");
            csp.setInt(1, serviPro.getCodigoBarras());
            csp.setString(2, serviPro.getNombre());
            csp.setDouble(3, serviPro.getPrecio());
            csp.setString(4, serviPro.getTipo());
            csp.setString(5, serviPro.getFabricante());
            csp.setString(6, serviPro.getDescripcion());
            
            csp.registerOutParameter(7, Types.VARCHAR);
            
            boolean hadResults = csp.execute();
            resultado =1;
            _mensaje = csp.getString(7);
        } catch (Exception e) {
            _mensaje=e.getMessage();
        }
        
        return resultado;
    }
    public int EliminarProductoServicio(int codBarra) throws SQLException{
        int resultado = -1;
        
        try {
            CallableStatement csp = _cnn.prepareCall("{call EliminarServicioProducto(?,?)}");
            csp.setInt(1, codBarra);
            
            csp.registerOutParameter(2, Types.VARCHAR);
            
            boolean hadResults = csp.execute();
            resultado =1;
            _mensaje = csp.getString(2);
        } catch (Exception e) {
            _mensaje=e.getMessage();
        }
        
        return resultado;
    }
    public List<EntidadServiciosProductos> ListarServiPro(String condicion)throws SQLException{
        List<EntidadServiciosProductos> arreglo = new ArrayList();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "Select CODIGOBARRA,NOMBRE,PRECIO,DESCRIPCION, TIPO from SERVICIO_PRODUCTO";
            if(!condicion.equals("")){
                sentencia = String.format("%S where %S", sentencia,condicion);
            }
            rs = stm.executeQuery(sentencia);
            
            while (rs.next()) {
                arreglo.add(new EntidadServiciosProductos(rs.getInt("CODIGOBARRA"), rs.getString("NOMBRE"), rs.getDouble("PRECIO"),rs.getString("DESCRIPCION"),rs.getString("TIPO")));
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn = null;
        }
        return arreglo;
    }
    public ResultSet ListarServiProCant(String condicion)throws SQLException{
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "Select i.CODIGOBARRA,NOMBRE,PRECIO,DESCRIPCION,CANTIDAD from SERVICIO_PRODUCTO sp inner join INVENTARIO i  on sp.CODIGOBARRA = i.CODIGOBARRA";
            if(!condicion.equals("")){
                sentencia = String.format("%S where %S", sentencia,condicion);
            }
            rs = stm.executeQuery(sentencia);
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn = null;
        }
        return rs;
    }
}
