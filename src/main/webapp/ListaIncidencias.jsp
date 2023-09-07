<%-- 
    Document   : ListaIncidencias
    Created on : 5/09/2023, 01:38:41
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <br>
        <div class="container">
            <c:if test="${incidencias != null}">
            <h1>Lista de Libros</h1>
            <table id="listTareas" class="table table-striped table-sm" cellspacing="0" width="100%">
                <thead class="bg-dark text-white">
                    <tr>
                        <td>No. Usuario</td>
                        <th>ISBN</th>
                        <th>Descripción</th>
                    </tr>
                </thead>               
                <tbody>
                    <c:forEach items="${incidencias}" var="incidencia">
                        <tr>
                            <td>${incidencia.noUsuario}</td>
                            <td>${incidencia.isbn}</td>
                            <td>${incidencia.descripcion}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </c:if>
            <c:if test="${empty(incidencias)}">
                <div class="alert alert-danger" role="alert">
                    No hay Incidencias Pendientes!
                </div>
            </c:if>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
