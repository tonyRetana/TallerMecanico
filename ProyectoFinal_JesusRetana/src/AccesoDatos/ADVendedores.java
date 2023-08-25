/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.EntidadCliente;
import Entidades.EntidadVendedor;
import Entidades.Ventas;
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
 * @author PC
 */
public class ADVendedores {
    //atributos
    private Connection _cnn;
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public ADVendedores() throws Exception{
        try{
            String url=Config.Config.getConnectionString();
            _cnn = DriverManager.getConnection(url); 
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public List<EntidadVendedor> ListarVendedores(String condicion)throws SQLException{
        List<EntidadVendedor> arreglo = new ArrayList();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT IDENTIFICACION, NOMBRE, PRIMERAPELLIDO, SEGUNDOAPELLIDO, TELEFONO, CORREO FROM VENDEDOR";
            if(!condicion.equals("")){
                sentencia = String.format("%S where %S", sentencia,condicion);
            }
            rs = stm.executeQuery(sentencia);
            
            while (rs.next()) {
                arreglo.add(new EntidadVendedor(rs.getInt("IDENTIFICACION"), rs.getString("NOMBRE"), rs.getString("PRIMERAPELLIDO"),rs.getString("SEGUNDOAPELLIDO"),rs.getString("TELEFONO"),rs.getString("CORREO")));
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn = null;
        }
        return arreglo;
    }//listar vendedores
    
    public int InsertarVendedor(EntidadVendedor vendedor) throws SQLException {
        int resultado = -1;
        try {
            CallableStatement csp = _cnn.prepareCall("{call InsertarVendedor(?,?,?,?,?,?,?,?)}");
            csp.setInt(1, vendedor.getIdentificacion());
            csp.setString(2, vendedor.getNombre());
            csp.setString(3, vendedor.getPrimerApellido());
            csp.setString(4, vendedor.getSegundoApellido());
            csp.setString(5, vendedor.getTelefono());
            csp.setString(6, vendedor.getCorreoElectronico());
            csp.setBoolean(7, vendedor.getActivo());

            // Agregar este parámetro para el mensaje de salida
            csp.registerOutParameter(8, Types.VARCHAR);

            boolean bool = csp.execute();
            _mensaje = csp.getString(8);
            resultado = 1;
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int ActualizarVendedor(EntidadVendedor vendedor)throws SQLException{
        int resultado =-1;
        try {
            String sentencia = "UPDATE VENDEDOR SET NOMBRE = ?,PRIMERAPELLIDO=?, SEGUNDOAPELLIDO=?, TELEFONO=?, CORREO=?, ACTIVO=? WHERE IDENTIFICACION = ?";
            PreparedStatement pst = _cnn.prepareStatement(sentencia);

            pst.setString(1, vendedor.getNombre());
            pst.setString(2, vendedor.getPrimerApellido());
            pst.setString(3, vendedor.getSegundoApellido());
            pst.setString(4, vendedor.getTelefono());
            pst.setString(5, vendedor.getCorreoElectronico());
            pst.setBoolean(6, vendedor.getActivo());
            pst.setInt(7, vendedor.getIdentificacion());
            
            resultado = pst.executeUpdate();
            
            _mensaje = "Se Actualizaron los datos";
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
}
