<%-- 
    Document   : frmMenuInicio
    Created on : 5 sep. 2023, 18:22:18
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
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
            <a class="navbar-brand" href="#">Taller Mecánico Retana</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </nav>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header">Iniciar Sesión</div>
                    <div class="card-body">
                        <form action="InicioSesion" method="POST">
                            <div class="form-group">
                                <label for="cedula">Cédula</label>
                                <input type="text" class="form-control" id="cedula" name="cedula" placeholder="Ingrese su cédula" required>
                            </div>
                            <div class="form-group">
                                <label for="contrasena">Contraseña</label>
                                <input type="password" class="form-control" id="contrasena" name="contrasena" placeholder="Ingrese su contraseña" required>
                            </div>
                            <button type="submit" class="btn btn-outline-info">Iniciar Sesión</button>
                            <button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#crearCuentaModal">Crear Cuenta</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal para Crear Cuenta -->
    <form id="crearCuentaForm" action="AgregarCliente" method="POST">
        <div class="modal fade" id="crearCuentaModal" tabindex="-1" role="dialog" aria-labelledby="crearCuentaModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="crearCuentaModalLabel">Crear Cuenta</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="cedulaRegistro">Cédula</label>
                            <input type="text" class="form-control" id="cedulaRegistro" name="cedulaRegistro" placeholder="Ingrese su cédula" required>
                            <div class="invalid-feedback">Este campo es obligatorio.</div>
                        </div>
                        <div class="form-group">
                            <label for="nombreRegistro">Nombre</label>
                            <input type="text" class="form-control" id="nombreRegistro" name="nombreRegistro" placeholder="Ingrese su nombre" required>
                            <div class="invalid-feedback">Este campo es obligatorio.</div>
                        </div>
                        <div class="form-group">
                            <label for="primerApellidoRegistro">Primer Apellido</label>
                            <input type="text" class="form-control" id="primerApellidoRegistro" name="primerApellidoRegistro" placeholder="Ingrese su primer apellido" required>
                            <div class="invalid-feedback">Este campo es obligatorio.</div>
                        </div>
                        <div class="form-group">
                            <label for="segundoApellidoRegistro">Segundo Apellido</label>
                            <input type="text" class="form-control" id="segundoApellidoRegistro" name="segundoApellidoRegistro" placeholder="Ingrese su segundo apellido" required>
                            <div class="invalid-feedback">Este campo es obligatorio.</div>
                        </div>
                        <div class="form-group">
                            <label for="telefonoRegistro">Teléfono</label>
                            <input type="text" class="form-control" id="telefonoRegistro" name="telefonoRegistro" placeholder="Ingrese su teléfono" required>
                            <div class="invalid-feedback">Este campo es obligatorio.</div>
                        </div>
                        <div class="form-group">
                            <label for="correoElectronicoRegistro">Correo Electrónico</label>
                            <input type="email" class="form-control" id="correoElectronicoRegistro" name="correoElectronicoRegistro" placeholder="Ingrese su correo electrónico" required>
                            <div class="invalid-feedback">Este campo es obligatorio y debe ser una dirección de correo válida.</div>
                        </div>
                        <div class="form-group">
                            <label for="contrasenaRegistro">Contraseña</label>
                            <input type="password" class="form-control" id="contrasenaRegistro" name="contrasenaRegistro" placeholder="Ingrese su contraseña" required>
                            <div class="invalid-feedback">Este campo es obligatorio.</div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-primary">Crear</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <% String mensaje = (String) request.getAttribute("mensaje"); %>
    <% if (mensaje != null) { %>
    <div class="alert alert-success fixed-bottom m-0" role="alert">
        <%= mensaje %>
        
    </div>
    <% } %>
    <% String mensajeValidacion = (String) request.getAttribute("mensajeValidacion"); %>
    <% if (mensajeValidacion != null) { %>
    <div class="alert alert-success fixed-bottom m-0" role="alert">
        <%if(mensajeValidacion.equals("ENTRADAVALIDA")){%>
            <% response.sendRedirect("frmOpcionesCliente.jsp"); %>
        <% } else{%>
            <%= mensajeValidacion %>
        <% } %>
    </div>
    <% } %>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
</body>
</html>

