<%-- 
    Document   : frmOpcionesCliente
    Created on : 6 sep. 2023, 14:47:29
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<html>
<head>
    <title>Iniciar Sesión</title>
    <!-- Incluye tus enlaces a CSS y JavaScript de Bootstrap aquí -->
    <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
</head>
<body style="background-color: #f2d7d5;">
    <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
        <div class="container">
            <a class="navbar-brand" href="#">Taller Mecanico Retana</a>
        </div>
    </nav>
    <section class="container">
        <div class="m-4">
            <h1>Bienvenido, ${nombre}</h1>
            <p>Selecciona una de las opciones a continuación para comenzar:</p>
        </div>
        <div class="row m-2">
            <div class="col-4">
                <div>
                    <div class="card text-white bg-danger mb-3">
                        <div class="card-header">Datos Cliente</div>
                        <div class="card-body">
                            <h6>Ingrese desde aqui para actualizar sus datos personales</h6>
                            <button type="submit" id="btnActualizar" class="btn btn-outline-warning">Actualizar Datos</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-4">
                <div>
                    <div class="card text-white bg-danger mb-3">
                        <div class="card-header">Factura</div>
                        <div class="card-body">
                            <h6>Compre todos nuestros articulos desde aqui</h6>
                            <button type="submit" id="btnFacturar" class="btn btn-outline-warning">Ir de Compras</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-4">
                <div>
                    <div class="card text-white bg-danger mb-3">
                        <div class="card-header">Historial de Compras</div>
                        <div class="card-body">
                            <h6>Revise su historial de Compras desde aqui</h6>
                            <button type="submit" id="btnHistorial" class="btn btn-outline-warning">Historial Facturas</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
            
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
    <script>
        // Obtenemos los botones por su ID
        var btnActualizarDatos = document.getElementById("btnActualizar");
        var btnIrDeCompras = document.getElementById("btnFacturar");
        var btnHistorialFacturas = document.getElementById("btnHistorial"); // Corregido el ID

        // Asignamos un evento clic a cada botón
        btnActualizarDatos.addEventListener("click", function() {
            // Redireccionar al formulario JSP correspondiente
            window.location.href = "frmDatosCliente.jsp";
        });

        btnIrDeCompras.addEventListener("click", function() {
            // Establecer el atributo de sesión numeroFactura como null
            <%-- Esto establecerá el atributo de sesión numeroFactura como null --%>
            <% session.setAttribute("numeroFactura", null); %>

            // Redireccionar al formulario JSP correspondiente
            window.location.href = "frmFactura.jsp";
        });

        btnHistorialFacturas.addEventListener("click", function() {
            // Redireccionar al formulario JSP correspondiente
            window.location.href = "frmHistorialCompras.jsp";
        });
    </script>

</body>
</html>
