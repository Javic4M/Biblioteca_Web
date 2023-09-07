<%-- 
    Document   : VisualizarSolicitudesDePrestamo
    Created on : 2/09/2023, 17:44:24
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mycompany.bibliotecaweb.basededatos.PrestamoBD"%>
<%@page import="com.mycompany.bibliotecaweb.tipos.Prestamo"%>
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
        <div class="container">
            <c:if test="${solicitudes != null}">
                <h1>Lista de Solicitudes Pendientes</h1>
                <c:if test="${!empty(prestamo)}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <strong>Informe: </strong> ${prestamo}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                <table id="listTareas" class="table table-striped table-sm" cellspacing="0" width="100%">
                    <thead class="bg-dark text-white">
                        <tr>
                            <th>Codigo</th>
                            <th>No. Biblioteca</th>
                            <th>No. Usuario</th>
                            <th>ISBN</th>
                            <th>Días Solicitados</th>
                            <th>Fecha de Solicitud</th>
                            <th>Aceptar</th>
                            <th>Rechazar</th>
                        </tr>
                    </thead>               
                    <tbody>
                        <c:forEach items="${solicitudes}" var="solicitud">
                            <tr>
                                <td>${solicitud.noSolicitudPrestamo}</td>
                                <td>${solicitud.noBiblioteca}</td>
                                <td>${solicitud.noUsuario}</td>
                                <td>${solicitud.isbn}</td>
                                <td>${solicitud.dias}</td>
                                <td>${solicitud.fechaEmision}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/SolicitudPrestamoServer?accion=aceptar&codigo=${solicitud.noSolicitudPrestamo}"
                                        class="btn btn-info btn-sm">Aceptar</a>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/SolicitudPrestamoServer?accion=rechazar&codigo=${solicitud.noSolicitudPrestamo}"
                                        class="btn btn-info btn-sm">Rechazar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty(solicitudes)}">
                <div class="alert alert-danger" role="alert">
                    No hay solicitudes Pendientes!
                </div>
            </c:if>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
