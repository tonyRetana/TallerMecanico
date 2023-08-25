/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

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
public class ADVentas {
    private Connection _cnn;
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public ADVentas() throws Exception{
        try{
            String url=Config.Config.getConnectionString();
            _cnn = DriverManager.getConnection(url); 
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public int InsertarVenta(Ventas venta) throws SQLException{
        int numFactura = -1;
        
        try {
            CallableStatement csp = _cnn.prepareCall("{call InsertarCompraVenta(?,?,?,?)}");
            
            csp.setInt(1, venta.getNumeroFactura());
            csp.setString(2, "Venta");
            csp.setDouble(3, venta.getTotalVenta());
            csp.registerOutParameter(4, Types.VARCHAR);
            
            boolean hadResults = csp.execute();
            _mensaje = csp.getString(4);
        } catch (Exception e) {
            _mensaje=e.getMessage();
        }
        
        return numFactura;
    }
    public ResultSet ObtenerVentas(String condicion) throws SQLException{
        ResultSet rs=null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "select NUMFACTURA,TOTAL,FECHAHORA from VENTAS ";
            if(!condicion.equals("")){
                sentencia = String.format("%s  %s", sentencia,condicion);
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
