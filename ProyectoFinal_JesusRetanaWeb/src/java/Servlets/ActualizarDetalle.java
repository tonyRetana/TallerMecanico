/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.LogicaDetalle;
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
@WebServlet(name = "ActualizarDetalle", urlPatterns = {"/ActualizarDetalle"})
public class ActualizarDetalle extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idDetalleFactura = Integer.parseInt(request.getParameter("idDetalleFactura"));
        int nuevaCantidad = Integer.parseInt(request.getParameter("nuevaCantidad"));
        int codigoBarras = Integer.parseInt(request.getParameter("codigoBarras"));
        int cantidadVieja = Integer.parseInt(request.getParameter("cantidadVieja"));
        // Calcula el nuevo total utilizando regla de tres
        double precio = Double.parseDouble(request.getParameter("precio"));
        double nuevoTotal = (nuevaCantidad*precio)/cantidadVieja;
        int resultado=0;
        try {
            LogicaDetalle logica= new LogicaDetalle();
            resultado = logica.ActualizarDetalle(idDetalleFactura, nuevoTotal, nuevaCantidad-cantidadVieja, nuevaCantidad);
        } catch (Exception e) {
        }
        // Llama al método de la capa de acceso a datos para actualizar el detalle
        response.sendRedirect("frmFactura.jsp");
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
