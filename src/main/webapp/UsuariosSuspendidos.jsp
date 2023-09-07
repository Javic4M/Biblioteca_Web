<%-- 
    Document   : UsuariosSuspendidos
    Created on : 6/09/2023, 19:38:00
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
            <c:if test="${usuarios != null}">
                <h1>Usuarios Suspendidos</h1>
                <table id="listTareas" class="table table-striped table-sm" cellspacing="0" width="100%">
                    <thead class="bg-dark text-white">
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>Username</th>
                            <th>Email</th>
                        </tr>
                    </thead>               
                    <tbody>
                        <c:forEach items="${usuarios}" var="usuario">
                            <tr>
                                <td>${usuario.codigo}</td>
                                <td>${usuario.nombre}</td>
                                <td>${usuario.username}</td>
                                <td>${usuario.email}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty(usuarios)}">
                <div class="alert alert-danger" role="alert">
                    No hay Usuarios Suspendidos!
                </div>
            </c:if>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
