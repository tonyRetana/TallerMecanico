/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.EntidadCliente;
import Logica.LogicaCLlentes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "InicioSesion", urlPatterns = {"/InicioSesion"})
public class InicioSesion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cedula = request.getParameter("cedula");
        String contrasena = request.getParameter("contrasena");
        String condicion = "IDENTIFICACION = " + cedula + " and CONTRASENA = '" + contrasena + "'";

        // Llama a tu capa de acceso a datos para insertar el cliente
        LogicaCLlentes logica = new LogicaCLlentes();
        List<EntidadCliente> lista;
        try {
            lista = logica.ListarClientes(condicion);
            EntidadCliente cliente = lista.get(0);

            // Obtener los datos de la entidad cliente en ISO-8859-1
            String nombreISO8859 = cliente.getNombre();
            String primerApellidoISO8859 = cliente.getPrimerApellido();
            String segundoApellidoISO8859 = cliente.getSegundoApellido();
            String telefonoISO8859 = cliente.getTelefono();
            String correoElectronicoISO8859 = cliente.getCorreoElectronico();

            // Convertir los datos de ISO-8859-1 a UTF-8
            String nombre = new String(nombreISO8859.getBytes("ISO-8859-1"), "UTF-8");
            String primerApellido = new String(primerApellidoISO8859.getBytes("ISO-8859-1"), "UTF-8");
            String segundoApellido = new String(segundoApellidoISO8859.getBytes("ISO-8859-1"), "UTF-8");
            String telefono = new String(telefonoISO8859.getBytes("ISO-8859-1"), "UTF-8");
            String correoElectronico = new String(correoElectronicoISO8859.getBytes("ISO-8859-1"), "UTF-8");

            // Verifica si la lista está vacía
            if (lista.isEmpty()) {
                request.setAttribute("mensajeValidacion", "Ingrese un usuario y contraseña válidos");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", cedula);
                session.setAttribute("contrasena", contrasena);
                session.setAttribute("nombre", nombre);
                session.setAttribute("primerApellido", primerApellido);
                session.setAttribute("segundoApellido", segundoApellido);
                session.setAttribute("telefono", telefono);
                session.setAttribute("correoElectronico", correoElectronico);
                response.sendRedirect("frmOpcionesCliente.jsp");
                return; // Importante, para detener la ejecución del servlet aquí
            }
        } catch (Exception e) {
            // Maneja las excepciones de SQL, muestra un mensaje de error
            request.setAttribute("mensaje", "Error de base de datos: " + e.getMessage());
        }

        // Si llegas aquí, significa que la redirección no se realizó
        RequestDispatcher dispatcher = request.getRequestDispatcher("frmMenuInicio.jsp");
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
