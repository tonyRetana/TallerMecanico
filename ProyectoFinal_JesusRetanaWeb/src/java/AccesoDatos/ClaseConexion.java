package AccesoDatos;
// @author Progra

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 
public class ClaseConexion {
    //Atributos
    //Cadena de conexión 
    private static final String connectionString =
 "jdbc:sqlserver://localhost:1433;databaseName=TALLER_MECANICO;user=sa;password=sa;";
    
    public static Connection getcConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        return DriverManager.getConnection(connectionString);
    }
    //Es una buena practica afregar metodos sobrecargados de Close
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    public static void close(Statement st) throws SQLException{
        st.close();
    }
    public static void close(PreparedStatement pst) throws SQLException{
        pst.close();
    }
    public static void close(CallableStatement cst) throws SQLException{
        cst.close();
    }
    public static void close(Connection conexion) throws SQLException{
        conexion.close();
    }
}
