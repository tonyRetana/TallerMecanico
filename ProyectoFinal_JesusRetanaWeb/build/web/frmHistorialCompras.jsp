<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.LogicaVendedor"%>
<%@page import="Entidades.EntidadVendedor"%>
<%@page import="java.util.List"%>
<%@page import="Logica.LogicaFactura"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    int idVendedores;
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Historial</title>
    <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="container">
        <h1 class="mt-5 text-center">Historial de Facturas del Último Mes</h1>

        <!-- Tabla para mostrar las facturas -->
        <table class="table table-striped mt-4">
            <thead>
                <tr>
                    <th>Número de Factura</th>
                    <th>Resumen</th>
                    <th>Fecha y Hora</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <%
                    LogicaFactura logica = new LogicaFactura();
                    String usuario = (String) session.getAttribute("usuario");
                    ResultSet rs = null;
                    
                    try {
                        // Obtener el ResultSet de ListarFactura
                        rs = logica.ListarFactura("WHERE IDCLIENTE = " + usuario + " AND FECHAHORA BETWEEN DATEADD(MONTH, -1, GETDATE()) AND GETDATE()");
                        
                        // Iterar a través del ResultSet y mostrar los datos en la tabla
                        while (rs.next()) {
                %>
                            <tr>
                                <td><%= rs.getString("NUMFACTURA") %></td>
                                <td><%= rs.getString("RESUMEN") %></td>
                                <td><%= rs.getString("FECHAHORA") %></td>
                                <td><%= rs.getString("TOTAL") %></td>
                            </tr>
                <%
                        }
                    } catch (Exception e) {
                        // Manejar excepciones aquí, por ejemplo, imprimir un mensaje de error
                        out.println("Error al obtener facturas: " + e.getMessage());
                    } finally {
                        // Cerrar el ResultSet en el bloque finally
                        if (rs != null) {
                            rs.close();
                        }
                    }
                %>
            </tbody>
        </table>
            
        <a href="frmOpcionesCliente.jsp" class="btn btn-primary mt-3">Ir a Opciones de Cliente</a>
        <button class="btn btn-primary mt-3" data-toggle="modal" data-target="#facturasModal">Abrir Factura Vendedor</button>
        
        <form class="mt-4" id="formBuscar">
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="vendedor">Selecciona un vendedor:</label>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID Vendedor</th>
                                <th>Nombre</th>
                                <th>Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                // Aquí realizas una consulta a la base de datos para obtener la lista de vendedores
                                LogicaVendedor logicaVen = new LogicaVendedor();
                                List<EntidadVendedor> vendedores = logicaVen.ListarVendedores(""); // Ajusta la condición según tu lógica
                                for (EntidadVendedor vendedor : vendedores) {
                            %>
                            <tr>
                                <td><%= vendedor.getIdentificacion() %></td>
                                <td><%= vendedor.getNombre() %></td>
                                <td>
                                    <a href="ListarFacturasServlet?vendedor=<%= vendedor.getIdentificacion() %>" class="btn btn-info">Seleccionar</a>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
        
        
        <div class="modal fade" id="facturasModal" tabindex="-1" role="dialog" aria-labelledby="facturasModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="facturasModalLabel">Facturas del Vendedor</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Aquí se mostrarán las facturas del vendedor seleccionado -->
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Número de Factura</th>
                                    <th>Resumen</th>
                                    <th>Fecha y Hora</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody id="tbodyVendedor">
                                <%
                                    LogicaFactura logicaFa = new LogicaFactura();
                                    String usuarioFA = (String) session.getAttribute("usuario");
                                    String idVendedor = (String) session.getAttribute("idVendedor");
                                    ResultSet rs2 = null;
                                        
                                    try {
                                        // Obtener el ResultSet de ListarFactura
                                        rs2 = logicaFa.ListarFactura("WHERE IDCLIENTE = " + usuarioFA + " AND IDVENDEDOR= "+idVendedor);

                                        // Iterar a través del ResultSet y mostrar los datos en la tabla
                                        while (rs2.next()) {
                                %>
                                            <tr>
                                                <td><%= rs2.getString("NUMFACTURA") %></td>
                                                <td><%= rs2.getString("RESUMEN") %></td>
                                                <td><%= rs2.getString("FECHAHORA") %></td>
                                                <td><%= rs2.getString("TOTAL") %></td>
                                            </tr>
                                <%
                                        }
                                    } catch (Exception e) {
                                        // Manejar excepciones aquí, por ejemplo, imprimir un mensaje de error
                                        out.println("Error al obtener facturas: " + e.getMessage());
                                    } finally {
                                        // Cerrar el ResultSet en el bloque finally
                                        if (rs2 != null) {
                                            rs2.close();
                                        }
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
  


</body>
</html>
