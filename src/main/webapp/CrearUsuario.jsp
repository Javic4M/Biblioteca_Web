<%-- 
    Document   : CrearUsuario
    Created on : 31/08/2023, 23:49:45
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prestamos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <br>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title text-center">Crear Usuario</h3>
                            <form method="POST" id="form-login" action="${pageContext.request.contextPath}/CrearUsuarioServer">
                                <div class="mb-3">
                                    <label>Nombre:</label>
                                    <input class="form-control" id="TipoDeBusqueda" name="nombre"/>
                                </div>
                                <div class="mb-3">
                                    <label>Username:</label>
                                    <input class="form-control" id="TipoDeBusqueda" name="username"/>
                                </div>
                                <div class="mb-3">
                                    <label>Password:</label>
                                    <input type="password" class="form-control" id="TipoDeBusqueda" name="password"/>
                                </div>
                                <div class="mb-3">
                                    <label>Email:</label>
                                    <input type="email" class="form-control" id="TipoDeBusqueda" name="email"/>
                                </div>
                                <c:if test="${user.puesto == 1}">
                                    <div class="mb-3">
                                        <label>Tipo de Usuario:</label>
                                        <select class="form-select" aria-label="Default select example" name="tipo">
                                            <option value="2">Recepcionistas</option>
                                            <option value="3">Transportistas</option>
                                        </select>
                                    </div>
                                </c:if>
                                <c:if test="${user.puesto == 1}">
                                    <div class="mb-3">
                                        <label>No. de Biblioteca:</label>
                                        <input class="form-control" type="number" name="noBiblioteca"/>
                                    </div>
                                </c:if>
                                <c:if test="${!empty(informacion)}">
                                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                                        <strong>Informacion: </strong> ${informacion}
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                                </c:if>
                                <c:if test="${!empty(error)}">
                                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                        <strong>Error: </strong> ${error}
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                                </c:if>
                                <div class="text-center mt-4">
                                    <button class="btn btn-primary" type="submit" >Crear</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
