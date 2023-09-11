/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.EntidadFactura;
import Logica.LogicaFactura;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
@WebServlet(name = "ActualizarFactura", urlPatterns = {"/ActualizarFactura"})
public class ActualizarFactura extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        int idVendedor = Integer.parseInt(request.getParameter("idVendedor"));
        double total = Double.parseDouble(request.getParameter("total"));
        String resumenISO8859 = request.getParameter("resumen");
        String numeroFacturaISO8859 = request.getParameter("numeroFactura");
        String resumen = new String(resumenISO8859.getBytes("ISO-8859-1"), "UTF-8");
        String numeroFactura = new String(numeroFacturaISO8859.getBytes("ISO-8859-1"), "UTF-8");
        // Llamar al método ActualizarFactura de la capa lógica para crear la factura
        LogicaFactura logicaFactura = new LogicaFactura();
        try {
            // Crear una instancia de EntidadFactura con los parámetros
            EntidadFactura factura = new EntidadFactura();
            factura.setIdCliente(idCliente);
            factura.setIdVendedor(idVendedor);
            factura.setTotal(total);
            factura.setResumen(resumen);
            factura.setNumeroFactura(Integer.parseInt(numeroFactura));
            int resultado = logicaFactura.ActualizarFactura(factura);

            
            // Si la factura se crea con éxito, puedes redirigir a una página de confirmación
            if (resultado > 0) {
                response.sendRedirect("frmOpcionesCliente.jsp");
            } else {
                // Si falla, maneja el error apropiadamente
                response.getWriter().println("Error al crear la factura.");
            }
        } catch (Exception e) {
            // Maneja las excepciones de SQL apropiadamente
            e.printStackTrace();
            response.getWriter().println("Error de base de datos al crear la factura.");
        }
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
