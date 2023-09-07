<%-- 
    Document   : Prestamo
    Created on : 30/08/2023, 00:55:16
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.mycompany.bibliotecaweb.basededatos.LibroBD"%>
<%@page import="com.mycompany.bibliotecaweb.tipos.Libro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prestamos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>       
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title text-center">Prestamo</h3>
                            <form method="POST" action="${pageContext.request.contextPath}/PrestamoServer">
                                <div class="mb-3">
                                    <label>ISBN</label>
                                    <input class="form-control" name="no_Libro" value="${libro.ISBN}" aria-label="readonly input example" readonly/>
                                </div>
                                <div class="mb-3">
                                    <label>Nombre:</label>
                                    <input class="form-control" value="${libro.nombre}" aria-label="readonly input example" readonly/>
                                </div>
                                <div class="mb-3">
                                    <label>Autor:</label>
                                    <input class="form-control" value="${libro.autor}" aria-label="readonly input example" readonly/>
                                </div>
                                <div class="mb-3">
                                    <label>Codigo del Usuario</label>
                                    <input class="form-control" type="number" name="idUsuario"/>
                                </div>
                                <div class="mb-3">
                                    <label>Fecha de Devolución</label>
                                    <input type="date" name="fechaDevolucion"/>
                                </div>
                                <c:if test="${!empty(error)}">
                                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                        <strong>Error: </strong> ${error}
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                                </c:if>
                                <div class="text-center mt-3">
                                    <button class="btn btn-primary" type="submit">Verificar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
