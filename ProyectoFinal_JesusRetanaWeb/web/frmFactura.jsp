<%@page import="Entidades.EntidadServiciosProductos"%>
<%@page import="java.util.List"%>
<%@page import="Logica.LogicaServiPro"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Logica.LogicaDetalle"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Comprar Productos</title>
    <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
    <script>
        // Deshabilita la función de retroceso del navegador
        history.pushState(null, null, location.href);
        window.onpopstate = function () {
            history.go(1);
        };
    </script>

</head>
<body>
    <div class="container">
        <form action="CrearFactura" method="post">
            <!-- Columna izquierda para la lista de productos -->
            <div>
                <h1>Crear Factura</h1>
                <div class="form-group">
                    <label for="numeroFactura">Número de Factura:</label>
                    <input type="text" class="form-control w-25" id="numeroFactura" name="numeroFactura" value="<%= session.getAttribute("numeroFactura") %>" readonly>
                </div>
                <!-- Agregar un botón para abrir el modal -->
                <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#productosModal">
                    Ver Productos
                </button>
            </div>
            <!-- Columna derecha para los detalles de la factura -->
            <div>
                <!-- Aquí puedes mostrar los detalles de la factura -->
                <h2>Detalles de la Factura</h2>
                <table class="table">
                    <!-- Encabezados de la tabla -->
                    <thead>
                        <tr>
                            <th>Código de Barras</th>
                            <th>Num. Detalle</th>
                            <th>Nombre</th>
                            <th>Cantidad</th>
                            <th>Total</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <!-- Cuerpo de la tabla -->
                    <tbody>
                        <%-- Aquí debes obtener y mostrar los detalles de la factura usando tu capa de acceso a datos --%>
                        <%
                            // Llama a tu capa de acceso a datos para obtener los detalles de la factura relacionados con session.getAttribute("numeroFactura")
                            String numeroFactura = (String) session.getAttribute("numeroFactura");
                            LogicaDetalle logicaDe = new LogicaDetalle();
                            double totalDetalles = 0.0;
                            if (numeroFactura != null && !numeroFactura.isEmpty()) {
                                ResultSet detallesFactura = logicaDe.ObtenerDetalles("NUMFACTURA = '" + numeroFactura + "'");
                                while (detallesFactura.next()) {
                                    int idDetalle = detallesFactura.getInt("ID_DETALLE");
                                    int codigoBarras = detallesFactura.getInt("CODIGOBARRA");
                                    double totalDetalle = detallesFactura.getDouble("TOTAL");
                                    totalDetalles += totalDetalle;
                        %>
                        <tr>
                            <td><%= codigoBarras %></td>
                            <td><%= idDetalle %></td>
                            <td><%= detallesFactura.getString("NOMBRE") %></td>
                            <td><%= detallesFactura.getInt("CANTIDAD") %></td>
                            <td><%= totalDetalle %></td>
                            <td>
                                <button type="button" class="btn btn-primary actualizar-btn" data-toggle="modal" data-target="#actualizarModal"
                                    data-id-detalle="<%= idDetalle %>"
                                    data-codigo-barras="<%= codigoBarras %>"
                                    data-precio="<%= totalDetalle %>"
                                    data-cantidad="<%= detallesFactura.getInt("CANTIDAD") %>">
                                    Actualizar
                                </button>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                </table>
                
                <!-- Mostrar el total de los detalles -->
                <label>Total: <%= totalDetalles %></label>
                
                <!-- Calcular impuestos (13%) y precio final con descuento (5%) -->
                <%
                    double impuestos = totalDetalles * 0.13;
                    double precioFinal = totalDetalles + impuestos - (totalDetalles * 0.05);
                %>
            </div>
        </form>
        <form action="ActualizarFactura" method="post">
            <label>Impuestos (13%): <%= impuestos %></label>
            <hr>
            <label>Precio Final (con 5% de descuento): <%= precioFinal %></label>
            <hr>
            <!-- Agregar un botón para Facturar -->
            <input type="hidden" name="idCliente" id="idCliente" value="<%= session.getAttribute("usuario") %>">
            <input type="hidden" name="idVendedor" id="idVendedor" value="1"> <!-- Valor por defecto -->
            <input type="hidden" name="total" id="total" value="<%= precioFinal %>">
            <input type="hidden" id="numeroFactura" name="numeroFactura" value="<%= session.getAttribute("numeroFactura") %>">
            <input type="hidden" name="resumen" id="resumen" value="Compra en línea">
            <button type="submit" class="btn btn-primary m-1">Facturar</button> 
        </form>
        <form action="BorrarFactura" method="post">
            <hr>
            <input type="hidden" name="numeroFactura" id="numeroFactura" value="<%= session.getAttribute("numeroFactura") %>">
            <button type="submit" class="btn btn-danger m-1">Salir</button>
        </form>
    </div>
    
    <!-- Modal para mostrar la lista de productos -->
    <div class="modal fade" id="productosModal" tabindex="-1" role="dialog" aria-labelledby="productosModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="productosModalLabel">Lista de Productos</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- Agrega una tabla para mostrar los productos aquí -->
                    <table class="table">
                        <!-- Encabezados de la tabla -->
                        <thead>
                            <tr>
                                <th>Código de Barras</th>
                                <th>Nombre</th>
                                <th>Precio</th>
                                <th>Descripción</th>
                                <th>Tipo</th>
                                <th>Cantidad</th>
                                <th>Acción</th>
                            </tr>
                        </thead>
                        <!-- Cuerpo de la tabla -->
                        <tbody>
                            <%-- Aquí iteramos sobre los datos de acceso a datos y los mostramos en filas de la tabla --%>
                            <%
                                // Llama a tu capa de acceso a datos para obtener los datos
                                LogicaServiPro logica = new LogicaServiPro();
                                List<EntidadServiciosProductos> listaProductos = logica.ListarServiPro("TIPO = 'PRODUCTO'");

                                for (EntidadServiciosProductos producto : listaProductos) {
                            %>
                            <tr>
                                <td><%= producto.getCodigoBarras() %></td>
                                <td><%= producto.getNombre() %></td>
                                <td><%= producto.getPrecio() %></td>
                                <td><%= producto.getDescripcion() %></td>
                                <td><%= producto.getTipo() %></td>
                                <form action="AgregarDetalle" method="post">
                                    <td>
                                        <input type="number" name="cantidad" min="1" max="10" value="1" class="w-75">
                                        
                                        <input type="hidden" name="codigoBarras" value="<%= producto.getCodigoBarras() %>">
                                        <input type="hidden" name="numeroFactura" value="<%= session.getAttribute("numeroFactura") %>">
                                        <input type="hidden" name="precio" value="<%= producto.getPrecio() %>">
                                    </td>
                                    <td>
                                        <button type="submit" class="btn btn-success">Agregar</button>
                                    </td>
                                </form>
                            </tr>
                            <%
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
    <!-- Modal de Actualización -->
    <div class="modal fade" id="actualizarModal" tabindex="-1" role="dialog" aria-labelledby="actualizarModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="actualizarModalLabel">Actualizar Cantidad</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- Formulario de actualización -->
                    <form action="ActualizarDetalle" method="post">
                        <!-- Campos de actualización -->
                        <div class="form-group">
                            <label for="nuevaCantidad">Nueva Cantidad:</label>
                            <input type="number" class="form-control" id="nuevaCantidad" name="nuevaCantidad" min="1" max="10" value="1" required>
                        </div>
                        <input type="hidden" id="idDetalleFactura" name="idDetalleFactura" value="">
                        <input type="hidden" id="codigoBarras" name="codigoBarras" value="">
                        <input type="hidden" id="precio" name="precio" value="">
                        <input type="hidden" id="cantidadVieja" name="cantidadVieja" value="">
                        <button type="submit" class="btn btn-primary">Actualizar</button>
                    </form>

                    <!-- Formulario de eliminación -->
                    <form action="BorrarDetalle" method="post">
                        <input type="hidden" id="idDetalleFacturaEliminar" name="idDetalleFacturaEliminar" value="">
                        <input type="hidden" id="codigoBarrasEliminar" name="codigoBarrasEliminar" value="">
                        <input type="hidden" id="cantidadEliminar" name="cantidadEliminar" value="">
                        <button type="submit" class="btn btn-danger mt-1">Eliminar</button>
                    </form>
                </div>
            </div>
        </div>
        
    </div>


    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
    <script>
        $(document).ready(function() {
            $('.actualizar-btn').click(function() {
                var idDetalle = $(this).data('id-detalle');
                var codigoBarras = $(this).data('codigo-barras');
                
                // Llena los campos ocultos en el formulario del modal con la información correspondiente
                $('#idDetalleFactura').val(idDetalle);
                $('#codigoBarras').val(codigoBarras);
            });
        });
    </script>
    <script>
        // Este código se ejecutará cuando la página se cargue completamente
        window.addEventListener('load', function() {
            var numeroFactura = "<%= session.getAttribute("numeroFactura") %>";
            
            if (numeroFactura === null || numeroFactura === "null") {
                var xhr = new XMLHttpRequest();

                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4) {
                        if (xhr.status === 200) {
                            // La solicitud al servlet se ha completado con éxito
                            // Ahora puedes recargar la página para mostrar los detalles actualizados
                            location.reload();
                        } else {
                            // Si la solicitud al servlet falla, puedes mostrar un mensaje de error o manejarlo según tus necesidades
                            console.error('Error al llamar al servlet CrearFactura.');
                        }
                    }
                };

                // Configura la solicitud para llamar al servlet CrearFactura usando el método GET
                xhr.open('GET', 'CrearFactura', true);
                xhr.send();
            }
        });
    </script>
    <script>
        $(document).ready(function() {
            $('.actualizar-btn').click(function() {
                var idDetalle = $(this).data('id-detalle');
                var codigoBarras = $(this).data('codigo-barras');
                var precio = $(this).data('precio');
                var cantidad = $(this).data('cantidad');

                // Llena los campos ocultos y visibles en el formulario del modal con la información correspondiente
                $('#idDetalleFactura').val(idDetalle);
                $('#codigoBarras').val(codigoBarras);
                $('#precio').val(precio);
                $('#cantidadVieja').val(cantidad);
                $('#nuevaCantidad').val(cantidad); // Establece la cantidad predeterminada como la cantidad actual
            });
        });
    </script>
    <script>
        $(document).ready(function() {
            $('.actualizar-btn').click(function() {
                var idDetalle = $(this).data('id-detalle');
                var codigoBarras = $(this).data('codigo-barras');
                var precio = $(this).data('precio');
                var cantidad = $(this).data('cantidad');

                // Llena los campos ocultos y visibles en el formulario de actualización con la información correspondiente
                $('#idDetalleFactura').val(idDetalle);
                $('#codigoBarras').val(codigoBarras);
                $('#precio').val(precio);
                $('#cantidadVieja').val(cantidad);
                $('#nuevaCantidad').val(cantidad); // Establece la cantidad predeterminada como la cantidad actual

                // Llena los campos ocultos en el formulario de eliminación con la misma información
                $('#idDetalleFacturaEliminar').val(idDetalle);
                $('#codigoBarrasEliminar').val(codigoBarras);
                $('#cantidadEliminar').val(cantidad);
            });

            // Limpia los campos ocultos en el formulario de eliminación cuando se cierra el modal
            $('#actualizarModal').on('hidden.bs.modal', function () {
                $('#idDetalleFacturaEliminar').val('');
                $('#codigoBarrasEliminar').val('');
                $('#cantidadEliminar').val('');
            });
        });
    </script>

</body>
</html>
