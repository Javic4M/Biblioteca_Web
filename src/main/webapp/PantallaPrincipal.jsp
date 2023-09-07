<%-- 
    Document   : PantallaPrincipal
    Created on : 26/08/2023, 23:13:51
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mycompany.bibliotecaweb.basededatos.UsuarioBD"%>
<%@page import="com.mycompany.bibliotecaweb.tipos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biblioteca Web</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <c:if test="${!empty(premiun)}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Informe: </strong> ${premiun}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <c:if test="${!empty(informacion)}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Informe: </strong> ${informacion}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <c:if test="${!empty(prestamoRealizado)}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Informe: </strong> ${prestamoRealizado}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <c:if test="${!empty(solicitudRealizada)}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Informe: </strong> ${solicitudRealizada}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <c:if test="${!empty(LibroNuevo)}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Informe: </strong> ${LibroNuevo}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <br>
        <div class="row justify-content-center">
            <div class="col-md-10">
                <div class="card">
                    <div class="card-body">
                        <br>
                        <h1>Biblioteca</h1>
                        <label>Selecciona el libro que deseas Visualizar</label>
                        <br>
                        <br>
                        <form action="${pageContext.request.contextPath}/BusquedaServer">
                            <div class="col">
                                <label>No. de Biblioteca:</label>
                                <input class="form-control" type="number" value="${user.biblioteca}" name="noBiblioteca"/>
                            </div>
                            <div class="col">
                                <label>Busqueda</label>
                                <select class="form-select" aria-label="Default select example" name="tipo">
                                    <option selected>isbn</option>
                                    <option value="nombre">nombre</option>
                                    <option value="autor">autor</option>
                                    <option value="categoria">categoria</option>
                                </select>
                            </div>
                            <div class="col">
                                <label>Busqueda</label>
                                <input class="form-control" id="TipoDeBusqueda" name="nombre"/><br>
                            </div>     
                            <button class="btn btn-primary" type="submit">Buscar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>