<%-- 
    Document   : Intermediario
    Created on : 24/08/2023, 17:27:23
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
            <h1>Lista de Prestamos</h1>
            <table id="listTareas" class="table table-striped table-sm" cellspacing="0" width="100%">
                <thead class="bg-dark text-white">
                    <tr>
                        <th>Tipo de Usuario</th>
                        <th>Días de Prestamo</th>
                        <th>Límite de Libros</th>
                        <th>Descuento</th>
                        <th>Multa por Retraso</th>
                        <th>Dias de Suspension</th>
                        <th>Editar</th>
                    </tr>
                </thead>               
                <tbody>
                    <c:forEach items="${parametros}" var="prestamo">
                        <tr>
                            <td>${prestamo.tipoUsuario}</td>
                            <td>${prestamo.dias}</td>
                            <td>${prestamo.limLibros}</td>
                            <td>${prestamo.suscripcionCosto}</td>
                            <td>${prestamo.multaPorRetraso}</td>
                            <td>${prestamo.diasSuspendido}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/ParametrosUsuariosServer?accion=mostrarIndividual&tipo=${prestamo.tipoUsuario}"
                                    class="btn btn-info btn-sm">Editar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>