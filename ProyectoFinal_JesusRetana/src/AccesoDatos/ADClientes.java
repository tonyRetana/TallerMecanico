/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.EntidadCliente;
import Entidades.EntidadVendedor;
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
public class ADClientes {
    private Connection _cnn;
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }
    
    public ADClientes() throws Exception{
        try{
            String url=Config.Config.getConnectionString();
            _cnn = DriverManager.getConnection(url); 
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public List<EntidadCliente> ListarClientes(String condicion)throws SQLException{
        List<EntidadCliente> arreglo = new ArrayList();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT IDENTIFICACION, NOMBRE, PRIMERAPELLIDO, SEGUNDOAPELLIDO, TELEFONO, CORREO FROM CLIENTE";
            if(!condicion.equals("")){
                sentencia = String.format("%S where %S", sentencia,condicion);
            }
            rs = stm.executeQuery(sentencia);
            
            while (rs.next()) {
                arreglo.add(new EntidadCliente(rs.getInt("IDENTIFICACION"), rs.getString("NOMBRE"), rs.getString("PRIMERAPELLIDO"),rs.getString("SEGUNDOAPELLIDO"),rs.getString("TELEFONO"),rs.getString("CORREO")));
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
            _cnn = null;
        }
        return arreglo;
    }//listar clientes
    
    public int InsertarCliente(EntidadCliente cliente) throws SQLException {
        int resultado = -1;
        try {
            CallableStatement csp = _cnn.prepareCall("{call InsertarCliente(?,?,?,?,?,?,?,?)}");
            csp.setInt(1, cliente.getIdentificacion());
            csp.setString(2, cliente.getNombre());
            csp.setString(3, cliente.getPrimerApellido());
            csp.setString(4, cliente.getSegundoApellido());
            csp.setString(5, cliente.getTelefono());
            csp.setString(6, cliente.getCorreoElectronico());
            csp.setString(7, cliente.getContrasena());

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

    public EntidadCliente ObtenerClientePorIdentificacion(int identificacion) throws SQLException {
        EntidadCliente cliente = null;
        try {
            String sql = "SELECT * FROM CLIENTE WHERE IDENTIFICACION = ?";
            PreparedStatement ps = _cnn.prepareStatement(sql);
            ps.setInt(1, identificacion);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String contrasena = rs.getString("CONTRASENA");
                String nombre = rs.getString("NOMBRE");
                String primerApellido = rs.getString("PRIMERAPELLIDO");
                String segundoApellido = rs.getString("SEGUNDOAPELLIDO");
                String telefono = rs.getString("TELEFONO");
                String correoElectronico = rs.getString("CORREO");
                
                cliente = new EntidadCliente(contrasena, identificacion, nombre, primerApellido, segundoApellido, telefono, correoElectronico);
            }
            
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }
    
    public int ActualizarCliente(EntidadCliente cliente)throws SQLException{
        int resultado =-1;
        try {
            String sentencia = "UPDATE CLIENTE SET NOMBRE = ?,PRIMERAPELLIDO=?, SEGUNDOAPELLIDO=?, TELEFONO=?, CORREO=?, CONTRASENA=? WHERE IDENTIFICACION = ?";
            PreparedStatement pst = _cnn.prepareStatement(sentencia);

            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getPrimerApellido());
            pst.setString(3, cliente.getSegundoApellido());
            pst.setString(4, cliente.getTelefono());
            pst.setString(5, cliente.getCorreoElectronico());
            pst.setString(6, cliente.getContrasena());
            pst.setInt(7, cliente.getIdentificacion());
            
            resultado = pst.executeUpdate();
            
            _mensaje = "Se Actualizaron los datos";
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
