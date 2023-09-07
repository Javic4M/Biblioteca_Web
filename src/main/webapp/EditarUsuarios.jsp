<%-- 
    Document   : EditarLibros
    Created on : 5/09/2023, 13:45:26
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.mycompany.bibliotecaweb.basededatos.LibroBD"%>
<%@page import="com.mycompany.bibliotecaweb.tipos.Libro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editor Usuario</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>       
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title text-center">Editar</h3>
                            <form method="POST" action="${pageContext.request.contextPath}/EditarUsuarioServer?accion=actualizarRecepcionista">
                                <div class="mb-3">
                                    <label>Codigo:</label>
                                    <input class="form-control" name="codigo" value="${usuario.codigo}" aria-label="readonly input example" readonly/>
                                </div>
                                <div class="mb-3">
                                    <label>Nombre:</label>
                                    <input class="form-control" name="nombre" value="${usuario.nombre}"/>
                                </div>
                                <div class="mb-3">
                                    <label>Username:</label>
                                    <input class="form-control" name="username" value="${usuario.username}"/>
                                </div>
                                <div class="mb-3">
                                    <label>Email:</label>
                                    <input class="form-control" name="email" value="${usuario.email}"/>
                                </div>
                                <div class="col">
                                    <label>Estado:</label>
                                    <select class="form-select" aria-label="Default select example" name="tipo">
                                        <c:if test="${usuario.suspendido}">
                                            <option value="true">Suspendido</option>
                                            <option value="false">Activo</option>
                                        </c:if>
                                        <c:if test="${!usuario.suspendido}">
                                            <option value="false">Activo</option>
                                            <option value="true">Suspendido</option>
                                        </c:if>
                                    </select>
                                </div>
                                <c:if test="${!empty(error)}">
                                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                        <strong>Error: </strong> ${error}
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                                </c:if>
                                <div class="text-center mt-3">
                                    <button class="btn btn-primary" type="submit">Guardar Cambios</button>
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
