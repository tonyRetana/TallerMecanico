/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Entidades.EntidadCliente;
import Entidades.EntidadDetalleFactura;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Progra
 */
public class ADDetalleFactura {
    private Connection _cnn;
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public ADDetalleFactura() throws Exception{
        try{
            String url=Config.Config.getConnectionString();
            _cnn = DriverManager.getConnection(url); 
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public int InsertarDetalleFactura(EntidadDetalleFactura detalle)throws SQLException{
        int resultado =-1;
        ResultSet rs = null;
        try {
            String sentencia = "INSERT INTO DETALLE_FACTURA(CANTIDAD,CODIGOBARRA,NUMFACTURA,TOTAL) VALUES (?,?,?,?)";
            PreparedStatement pst = _cnn.prepareStatement(sentencia);

            pst.setInt(1, detalle.getCantidad());
            pst.setInt(2, detalle.getCodBarra());
            pst.setInt(3, detalle.getCodFactura());
            pst.setDouble(4, detalle.getTotal());
            resultado = pst.executeUpdate();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public ResultSet ListarDF(String condicion)throws SQLException{
        ResultSet rs=null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "select ID_DETALLE, NOMBRE, CANTIDAD, TOTAL from DETALLE_FACTURA DF inner join SERVICIO_PRODUCTO sp on df.CODIGOBARRA=sp.CODIGOBARRA";
            if(!condicion.equals("")){
                sentencia = String.format("%s where %s", sentencia,condicion);
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
    public int EliminarDetalle(int id)throws SQLException{
        int resultado =-1;
        ResultSet rs = null;
        try {
            String sentencia = "DELETE FROM DETALLE_FACTURA WHERE ID_DETALLE=?;";
            PreparedStatement pst = _cnn.prepareStatement(sentencia);

            pst.setInt(1, id);

            resultado = pst.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    public int ActualizarDetalle(int id, double total, int cantidad, int cantidadDe) throws SQLException {
        int resultado = -1;
        try {
            CallableStatement csp = _cnn.prepareCall("{call ActualizarDetalleFacturaEInventario(?,?,?,?,?)}");
            csp.setInt(1, id);
            csp.setDouble(2, total);
            csp.setInt(3, cantidad);
            csp.setInt(4, cantidadDe);

            // Agregar este parámetro para el mensaje de salida
            csp.registerOutParameter(5, Types.VARCHAR);

            boolean bool = csp.execute();
            _mensaje = csp.getString(5);
            resultado = 1;
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    public ResultSet ObtenerDetalle(String condicion) throws SQLException{
        ResultSet rs=null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "select CODIGOBARRA,CANTIDAD,TOTAL from DETALLE_FACTURA ";
            if(!condicion.equals("")){
                sentencia = String.format("%s WHERE %s", sentencia,condicion);
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
