<%-- 
    Document   : SolicitudesTransportes
    Created on : 6/09/2023, 10:08:32
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
            <h1>Lista de Solicitudes de Transporte</h1>
            <table id="listTareas" class="table table-striped table-sm" cellspacing="0" width="100%">
                <thead class="bg-dark text-white">
                    <tr>
                        <th>No.</th>
                        <th>No. Transportista</th>
                        <th>No. Usuario</th>
                        <th>ISBN</th>
                        <th>Dias</th>
                        <th>Fecha Emisión</th>
                        <th>Direccion</th>
                        <th>Estado</th>
                        <th>Marcar</th>
                    </tr>
                </thead>               
                <tbody>
                    <c:forEach items="${solicitudesTansportes}" var="solicitudesTansporte">
                        <tr>
                            <td>${solicitudesTansporte.no_transporte}</td>
                            <td>${solicitudesTansporte.no_transportista}</td>
                            <td>${solicitudesTansporte.no_usuario}</td>
                            <td>${solicitudesTansporte.isbn}</td>
                            <td>${solicitudesTansporte.dias}</td>
                            <td>${solicitudesTansporte.fecha_emision}</td>
                            <td>${solicitudesTansporte.direccion}</td>
                            <td>${solicitudesTansporte.estado}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/EditarInformacionLibro?accion=eliminar&isbn=${libro.ISBN}"
                                    class="btn btn-danger btn-sm">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
