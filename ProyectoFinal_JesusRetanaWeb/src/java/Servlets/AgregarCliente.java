/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.EntidadCliente;
import Logica.LogicaCLlentes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AgregarCliente", urlPatterns = {"/AgregarCliente"})
public class AgregarCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cedulaISO8859 = request.getParameter("cedulaRegistro");
        String nombreISO8859 = request.getParameter("nombreRegistro");
        String primerApellidoISO8859 = request.getParameter("primerApellidoRegistro");
        String segundoApellidoISO8859 = request.getParameter("segundoApellidoRegistro");
        String telefonoISO8859 = request.getParameter("telefonoRegistro");
        String correoElectronicoISO8859 = request.getParameter("correoElectronicoRegistro");
        String contrasenaISO8859 = request.getParameter("contrasenaRegistro");

        // Convertir los parámetros ISO-8859-1 a UTF-8
        String cedula = new String(cedulaISO8859.getBytes("ISO-8859-1"), "UTF-8");
        String nombre = new String(nombreISO8859.getBytes("ISO-8859-1"), "UTF-8");
        String primerApellido = new String(primerApellidoISO8859.getBytes("ISO-8859-1"), "UTF-8");
        String segundoApellido = new String(segundoApellidoISO8859.getBytes("ISO-8859-1"), "UTF-8");
        String telefono = new String(telefonoISO8859.getBytes("ISO-8859-1"), "UTF-8");
        String correoElectronico = new String(correoElectronicoISO8859.getBytes("ISO-8859-1"), "UTF-8");
        String contrasena = new String(contrasenaISO8859.getBytes("ISO-8859-1"), "UTF-8");


        // Crea una instancia de EntidadCliente y configúrala con los datos del formulario
        EntidadCliente cliente = new EntidadCliente();
        cliente.setIdentificacion(Integer.parseInt(cedula));
        cliente.setNombre(nombre);
        cliente.setPrimerApellido(primerApellido);
        cliente.setSegundoApellido(segundoApellido);
        cliente.setTelefono(telefono);
        cliente.setCorreoElectronico(correoElectronico);
        cliente.setContrasena(contrasena);

        // Llama a tu capa de acceso a datos para insertar el cliente
        LogicaCLlentes logica = new LogicaCLlentes(); // Reemplaza CapaDatos con el nombre real de tu clase
        try {
            int resultado = logica.InsertarCliente(cliente);
            request.setAttribute("mensaje", logica.getMensaje());
        } catch (Exception e) {
            // Maneja las excepciones de SQL, muestra un mensaje de error
            request.setAttribute("mensaje", "Error de base de datos: " + e.getMessage());
        }

        // Redirige de vuelta a la página de inicio de sesión o muestra un mensaje en el modal
        RequestDispatcher dispatcher = request.getRequestDispatcher("frmMenuInicio.jsp"); // Reemplaza con la página correcta
        dispatcher.forward(request, response);
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
