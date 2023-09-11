/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import static AccesoDatos.ClaseConexion.getcConnection;
import Entidades.EntidadFactura;
import Entidades.EntidadServiciosProductos;
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
public class ADFactura {
    private Connection _cnn;
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public ADFactura() throws Exception{
        try{
            _cnn = getcConnection(); 
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public int InsertarFactura() throws SQLException{
        int numFactura = -1;
        
        try {
            CallableStatement csp = _cnn.prepareCall("{call InsertarFacturaEnBlanco(?,?)}");
            
            csp.registerOutParameter(1, Types.INTEGER);
            csp.registerOutParameter(2, Types.VARCHAR);
            
            boolean hadResults = csp.execute();
            _mensaje = csp.getString(2);
            numFactura = csp.getInt(1);
        } catch (Exception e) {
            _mensaje=e.getMessage();
        }
        
        return numFactura;
    }
    
    public int EliminarFactura(int id) throws SQLException {
        int resultado = -1;
        Connection connection = _cnn;
        CallableStatement cs = null;

        try {
            // Obtén la conexión a la base de datos (connection) aquí

            // Llama al procedimiento almacenado
            cs = connection.prepareCall("{call EliminarFactura(?, ?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);

            cs.execute();

            // Obtiene el resultado del mensaje de estado
            String mensaje = cs.getString(2);
            System.out.println("Mensaje de estado: " + mensaje);

            resultado = 1; // Si llegas aquí, la ejecución fue exitosa
        } catch (SQLException e) {
            // Manejar errores
            e.printStackTrace();
            throw e;
        } finally {
            // Cerrar la conexión y el CallableStatement
            if (cs != null) {
                cs.close();
            }
            // Cierra la conexión (connection) aquí si es necesario
        }

        return resultado;
    }
    public ResultSet ListarFactura(String condicion)throws SQLException{
        ResultSet rs=null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "select NUMFACTURA,RESUMEN,FECHAHORA,TOTAL from FACTURA";
            
            sentencia = String.format("%s %s", sentencia,condicion);
            rs = stm.executeQuery(sentencia);
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn = null;
        }
        return rs;
    }
    public int ActualizarFactura(EntidadFactura factura)throws SQLException{
        int resultado =-1;
        ResultSet rs = null;
        try {
            String sentencia = "UPDATE FACTURA SET IDCLIENTE = ?, IDVENDEDOR = ?, TOTAL=?, RESUMEN=? where NUMFACTURA=?;";
            PreparedStatement pst = _cnn.prepareStatement(sentencia);

            pst.setInt(1, factura.getIdCliente());
            pst.setInt(2, factura.getIdVendedor());
            pst.setDouble(3, factura.getTotal());
            pst.setString(4, factura.getResumen());
            pst.setInt(5, factura.getNumeroFactura());

            resultado = pst.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
