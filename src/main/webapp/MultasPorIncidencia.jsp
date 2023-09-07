<%-- 
    Document   : MultasPorIncidencia
    Created on : 5/09/2023, 10:19:59
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
            <h1>Lista de Multas por Incidencias</h1>
            <c:if test="${incidencias != null}">
                <table id="listTareas" class="table table-striped table-sm" cellspacing="0" width="100%">
                    <thead class="bg-dark text-white">
                        <tr>
                            <th>No. Prestamo</th>
                            <th>Descripción</th>
                            <th>Pago</th>
                            <th>Realizar</th>
                        </tr>
                    </thead>               
                    <tbody>
                        <c:forEach items="${incidencias}" var="multa">
                            <tr>
                                <td name="ISBN">${multa.isbn}</td>
                                <td name="nombre">${multa.descripcion}</td>
                                <td name="autor">${multa.costo}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/MultasServer?accion=PagarIncidencia&isbn=${multa.isbn}"
                                        class="btn btn-danger btn-sm">Pagar Multa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty(incidencias)}">
                <div class="alert alert-danger" role="alert">
                    No tiene Multas Pendientes!
                </div>
            </c:if>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
