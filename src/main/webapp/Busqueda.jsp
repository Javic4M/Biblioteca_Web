<%-- 
    Document   : Busqueda
    Created on : 27/08/2023, 13:41:14
    Author     : DELL

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mycompany.bibliotecaweb.basededatos.LibroBD"%>
<%@page import="com.mycompany.bibliotecaweb.tipos.Libro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Usuarios</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <div class="container">
            <c:if test="${libros != null}">
                <h1>LISTADO DE LIBROS</h1>
                <table id="listTareas" class="table table-striped table-sm" cellspacing="0" width="100%">
                    <thead class="bg-dark text-white">
                        <tr>
                            <th>ISBN</th>
                            <th>No. Biblioteca</th>
                            <th>Nombre</th>
                            <th>Autor</th>
                            <th>Costo</th>
                            <th>Disponibilidad</th>
                        </tr>
                    </thead>               
                    <tbody>
                        <c:forEach items="${libros}" var="libro">
                            <tr>
                                <td name="ISBN">${libro.ISBN}</td>
                                <td name="ISBN">${libro.biblioteca}</td>
                                <td name="nombre">${libro.nombre}</td>
                                <td name="autor">${libro.autor}</td>
                                <td name="costo">${libro.costo}</td>
                                <c:if test="${user.puesto == 2}">
                                    <c:if test="${user.biblioteca == libro.biblioteca}">
                                        <c:if test="${libro.disponible}"> 
                                            <td>
                                                <a href="${pageContext.request.contextPath}/PrestamoServer?accion=formularioP&codigo=${libro.ISBN}"
                                                   class="btn btn-info btn-sm">Realizar Prestamo</a>
                                            </td>
                                        </c:if>
                                        <c:if test="${!libro.disponible}">
                                            <td>No Disponible</td>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${user.biblioteca != libro.biblioteca}">
                                        <c:if test="${libro.disponible}">
                                            <td>Disponible</td>
                                        </c:if>
                                        <c:if test="${!libro.disponible}">
                                            <td>No Disponible</td>
                                        </c:if>
                                    </c:if>
                                </c:if>
                                <c:if test="${user.puesto == 4}">
                                    <c:if test="${libro.disponible}"> 
                                        <c:if test="${!user.suspendido}">
                                            <td>
                                                <a href="${pageContext.request.contextPath}/SolicitudPrestamoServer?accion=formulario&codigo=${libro.ISBN}"
                                                    class="btn btn-info btn-sm">Realizar Solicitud</a>
                                            </td>
                                        </c:if>
                                        <c:if test="${user.suspendido}">
                                            <td>Estas Suspendido</td>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${!libro.disponible}">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/SolicitudPrestamoServer?accion=formulario&codigo=${libro.ISBN}"
                                                class="btn btn-danger btn-sm">Notificar</a>
                                        </td>
                                    </c:if>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty(libros)}">
                <div class="alert alert-danger" role="alert">
                    El libro no Existe ó no esta Disponible en este Momento!
                </div>
            </c:if>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>