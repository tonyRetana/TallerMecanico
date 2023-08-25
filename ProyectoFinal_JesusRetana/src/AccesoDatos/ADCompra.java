/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Compras;
import Entidades.Ventas;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 *
 * @author PC
 */
public class ADCompra {
    
     private Connection _cnn;
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public ADCompra() throws Exception{
        try{
            String url=Config.Config.getConnectionString();
            _cnn = DriverManager.getConnection(url); 
        }
        catch(Exception e){
            throw e;
        }
    }
    public int InsertarCompra(Compras compra,int codigoBarras) throws SQLException{
        int numFactura = -1;
        
        try {
            CallableStatement csp = _cnn.prepareCall("{call InsertarFacturaCompra(?,?,?,?,?)}");
            
            csp.setDouble(2, (compra.getTotalCompra() *compra.getCantidad()) );
            csp.setInt(3, codigoBarras);
            csp.setInt(4, compra.getCantidad());
            
            csp.registerOutParameter(5, Types.VARCHAR);
            csp.registerOutParameter(1, Types.INTEGER);
            boolean hadResults = csp.execute();
            _mensaje = csp.getString(4);
        } catch (Exception e) {
            _mensaje=e.getMessage();
        }
        
        return numFactura;
    }
    public ResultSet ObtenerCompras(String condicion) throws SQLException{
        ResultSet rs=null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "select NUMFACTURA,TOTAL,FECHAHORA from COMPRAS where TOTAL>0";
            if(!condicion.equals("")){
                sentencia = String.format("%s AND %s", sentencia,condicion);
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
