/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.EntidadDetalleFactura;
import Logica.LogicaDetalle;
import Logica.LogicaInventario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
@WebServlet(name = "AgregarDetalle", urlPatterns = {"/AgregarDetalle"})
public class AgregarDetalle extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int codigoBarras = Integer.parseInt(request.getParameter("codigoBarras"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        int numeroFactura=Integer.parseInt(request.getParameter("numeroFactura"));
        double precio = Double.parseDouble(request.getParameter("precio"));
        // Llama a tu capa de acceso a datos para actualizar el inventario y obtener el resultado
        LogicaInventario logica = new LogicaInventario();
        LogicaDetalle detaLogica = new LogicaDetalle();
        
        EntidadDetalleFactura detalle = new EntidadDetalleFactura();
        try {
            int resultadoActualizacion = logica.ActualizarInventario(codigoBarras, cantidad);
            String mensaje = "";

            if (resultadoActualizacion == 1) {
                mensaje = "Transacción exitosa. Detalle de factura insertado.";
                detalle.setCantidad(cantidad);
                detalle.setCodBarra(codigoBarras);
                detalle.setCodFactura(numeroFactura);
                detalle.setTotal(cantidad*precio);
                resultadoActualizacion = detaLogica.InsertarDetalleFactura(detalle);
            } else {
                mensaje = "No se pudo completar la transacción. " + logica.getMensaje(); // Obtener el mensaje de la capa de acceso a datos
            }

            // Puedes almacenar el mensaje en un atributo de solicitud para mostrarlo en el JSP
            request.setAttribute("mensaje", mensaje);
        } catch (Exception e) {
            e.printStackTrace(); // Esto imprimirá la excepción en la consola
            String mensaje = "Error al procesar la solicitud.";
            request.setAttribute("mensaje", mensaje);
        }

        // Redirigir de vuelta al JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("frmFactura.jsp");
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
