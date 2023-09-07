<%-- 
    Document   : VisualizarPrestamos
    Created on : 2/09/2023, 01:29:58
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
            <c:if test="${prestamos != null}">
                <h1>Lista de Prestamos</h1>
                <table id="listTareas" class="table table-striped table-sm" cellspacing="0" width="100%">
                    <thead class="bg-dark text-white">
                        <tr>
                            <th>Codigo</th>
                            <th>No. Biblioteca</th>
                            <th>ISBN</th>
                            <th>Fecha de Entrega</th>
                            <th>Multa</th>
                            <th>Pago</th>
                            <th>Finalizar</th>
                            <th>Incidencias</th>
                        </tr>
                    </thead>               
                    <tbody>
                        <c:forEach items="${prestamos}" var="prestamo">
                            <tr>
                                <td name="ISBN">${prestamo.noPrestamo}</td>
                                <td name="nombre">${prestamo.noBiblioteca}</td>
                                <td name="autor">${prestamo.ISBN}</td>
                                <td name="costo">${prestamo.fechaEntrega}</td>
                                <td name="multa">${prestamo.pago}</td>
                                <td name="pago">${prestamo.multa}</td>
                                <c:if test="${user.puesto == 2}">
                                    <td>
                                        <a href="${pageContext.request.contextPath}/FinalizarPrestamoServer?accion=Finalizar&isbn=${prestamo.ISBN}"
                                            class="btn btn-info btn-sm">Finalizar Prestamo</a>
                                    </td>
                                </c:if>
                                <c:if test="${user.puesto == 4}">
                                    <td>Se Finaliza en Biblioteca</td>
                                </c:if>
                                <td>
                                    <a href="${pageContext.request.contextPath}/PrestamoServer?accion=formularioI&codigo=${prestamo.ISBN}&usuario=${prestamo.noUsuario}"
                                        class="btn btn-danger btn-sm">Finalizar con Incidencia</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty(prestamos)}">
                <div class="alert alert-danger" role="alert">
                    No tiene Prestamos Activos!
                </div>
            </c:if>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
