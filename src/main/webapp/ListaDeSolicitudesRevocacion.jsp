<%-- 
    Document   : ListaDeSolicitudesRevocacion
    Created on : 6/09/2023, 23:39:21
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
            <table id="listTareas" class="table table-striped table-sm" cellspacing="0" width="100%">
                <h1>Lista de Solicitudes de Revocación</h1>
                <thead class="bg-dark text-white">
                    <tr>
                        <th>No. Usuario</th>
                        <th>Descripcion</th>
                        <th>Aceptar</th>
                        <th>Rechazar</th>
                    </tr>
                </thead>               
                <tbody>
                    <c:forEach items="${solicitudes}" var="solicitud">
                        <tr>
                            <td>${solicitud.noUsuario}</td>
                            <td>${solicitud.descripcion}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/SolicitudRevocacionServer?accion=aceptar&noUsuario=${solicitud.noUsuario}"
                                    class="btn btn-info btn-sm">Aceptar</a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/SolicitudRevocacionServer?accion=rechazar&noUsuario=${solicitud.noUsuario}"
                                    class="btn btn-danger btn-sm">Rechazar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
