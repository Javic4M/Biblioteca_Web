<%-- 
    Document   : ListaDeLibros
    Created on : 3/09/2023, 15:05:53
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
            <div class="col-md-11">
                <div class="card-body">
                    <div>
                        <br>
                        <h1>Lista de Libros</h1>
                        <form method="POST" action="${pageContext.request.contextPath}/BusquedaServer?accion=buscarLibrosEspecificos">
                            <div class="row">
                                <div class="col">
                                    <input class="form-control" placeholder="Número de Biblioteca" type="number" name="noBiblioteca"/><br>
                                </div> 
                                <div class="col">
                                    <button class="btn btn-primary" type="submit">Buscar</button>
                                </div> 
                            </div> 
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <table id="listTareas" class="table table-striped table-sm" cellspacing="0" width="100%">
                <thead class="bg-dark text-white">
                    <tr>
                        <th>ISBN</th>
                        <th>No. Biblioteca</th>
                        <th>Nombre</th>
                        <th>Autor</th>
                        <th>Costo</th>
                        <th>Editar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>               
                <tbody>
                    <c:forEach items="${libros}" var="libro">
                        <tr>
                            <td>${libro.ISBN}</td>
                            <td>${libro.biblioteca}</td>
                            <td>${libro.nombre}</td>
                            <td>${libro.autor}</td>
                            <td>${libro.costo}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/EditarInformacionLibro?accion=editar&isbn=${libro.ISBN}"
                                    class="btn btn-info btn-sm">Editar</a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/EditarInformacionLibro?accion=eliminar&isbn=${libro.ISBN}"
                                    class="btn btn-danger btn-sm">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="container">
            <a href="${pageContext.request.contextPath}/IngresoDeLibro.jsp" class="btn btn-info btn-sm">Ingresar Nuevo Libro</a>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
