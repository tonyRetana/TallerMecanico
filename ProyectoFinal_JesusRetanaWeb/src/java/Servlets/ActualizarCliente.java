/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.EntidadCliente;
import Logica.LogicaCLlentes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
@WebServlet(name = "ActualizarCliente", urlPatterns = {"/ActualizarCliente"})
public class ActualizarCliente extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Recuperar los datos del cliente de la sesión
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");

        // Recuperar los nuevos datos del formulario
        String nombre = request.getParameter("nombre");
        String primerApellido = request.getParameter("primerApellido");
        String segundoApellido = request.getParameter("segundoApellido");
        String telefono = request.getParameter("telefono");
        String correoElectronico = request.getParameter("correoElectronico");
        String contrasena = request.getParameter("contrasena");

        nombre = new String(nombre.getBytes("ISO-8859-1"), "UTF-8");
        primerApellido = new String(primerApellido.getBytes("ISO-8859-1"), "UTF-8");
        segundoApellido = new String(segundoApellido.getBytes("ISO-8859-1"), "UTF-8");
        telefono = new String(telefono.getBytes("ISO-8859-1"), "UTF-8");
        correoElectronico = new String(correoElectronico.getBytes("ISO-8859-1"), "UTF-8");
        contrasena = new String(contrasena.getBytes("ISO-8859-1"), "UTF-8");
        
        // Crear una instancia de EntidadCliente con los nuevos datos
        EntidadCliente clienteActualizado = new EntidadCliente();
        clienteActualizado.setIdentificacion(Integer.parseInt(usuario));
        clienteActualizado.setNombre(nombre);
        clienteActualizado.setPrimerApellido(primerApellido);
        clienteActualizado.setSegundoApellido(segundoApellido);
        clienteActualizado.setTelefono(telefono);
        clienteActualizado.setCorreoElectronico(correoElectronico);
        clienteActualizado.setContrasena(contrasena);
        
        // Llamar al método de actualización de la capa de acceso a datos
        try {
            session.setAttribute("nombre", nombre);
            session.setAttribute("primerApellido", primerApellido);
            session.setAttribute("segundoApellido", segundoApellido);
            session.setAttribute("telefono", telefono);
            session.setAttribute("correoElectronico", correoElectronico);
            LogicaCLlentes logica = new LogicaCLlentes();
            logica.ActualizarCliente(clienteActualizado);
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error de base de datos: " + e.getMessage());
        }

        // Redireccionar de nuevo a la página de inicio o mostrar un mensaje de éxito
        response.sendRedirect("frmDatosCliente.jsp");
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
