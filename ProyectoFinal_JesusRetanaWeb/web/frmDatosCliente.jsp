<%-- 
    Document   : frmDatosCliente
    Created on : 8 sep. 2023, 07:38:23
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
    <title>Actualizar datos</title>
    <!-- Incluye tus enlaces a CSS y JavaScript de Bootstrap aquí -->
    <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
</head>
<body style="background-color: #f2d7d5;">
    <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
        <div class="container">
            <a class="navbar-brand" href="#">Taller Mecanico Retana</a>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                  <a class="nav-link" href="frmOpcionesCliente.jsp">Inicio<span class="sr-only"></span></a>
                </li>
            </ul>
        </div>
    </nav>
    <section class="container">
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card rounded">
                        <div class="card-header bg-danger text-white">
                            <h5 class="card-title">Actualizar Datos Personales de ${nombre}</h5>
                        </div>
                        <div class="card-body">
                            <form action="ActualizarCliente" method="post">
                                <div class="form-group">
                                    <label for="nombre">Cedula:</label>
                                    <input type="text" class="form-control" name="usuario" value="${usuario}" readonly="true">
                                </div>
                                <div class="form-group">
                                    <label for="nombre">Nombre:</label>
                                    <input type="text" class="form-control" name="nombre" value="${nombre}" required>
                                </div>

                                <div class="form-group">
                                    <label for="primerApellido">Primer Apellido:</label>
                                    <input type="text" class="form-control" name="primerApellido" value="${primerApellido}" required>
                                </div>

                                <div class="form-group">
                                    <label for="segundoApellido">Segundo Apellido:</label>
                                    <input type="text" class="form-control" name="segundoApellido" value="${segundoApellido}" required>
                                </div>

                                <div class="form-group">
                                    <label for="telefono">Teléfono:</label>
                                    <input type="text" class="form-control" name="telefono" value="${telefono}" required>
                                </div>

                                <div class="form-group">
                                    <label for="correoElectronico">Correo Electrónico:</label>
                                    <input type="email" class="form-control" name="correoElectronico" value="${correoElectronico}" required>
                                </div>

                                <div class="form-group">
                                    <label for="contrasena">Contraseña:</label>
                                    <input type="password" class="form-control" name="contrasena" value="${contrasena}"required>
                                </div>

                                <input type="submit" class="btn btn-danger" value="Actualizar Datos"></input>
                                
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
            
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
</body>
</html>
