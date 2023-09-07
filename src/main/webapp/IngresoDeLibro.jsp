<%-- 
    Document   : IngresoDeLibro
    Created on : 5/09/2023, 23:04:24
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingreso</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <br>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title text-center">Ingresar Libro</h3>
                            <form method="POST" id="form-login" action="${pageContext.request.contextPath}/NuevoLibroServer">
                                <div class="mb-3">
                                    <label>ISBN:</label>
                                    <input type="number" class="form-control" id="TipoDeBusqueda" name="isbn"/>
                                </div>
                                <div class="mb-3">
                                    <label>No. Biblioteca:</label>
                                    <input type="number" class="form-control" name="noBiblioteca"/>
                                </div>
                                <div class="mb-3">
                                    <label>Nombre:</label>
                                    <input type="text" class="form-control" name="nombre"/>
                                </div>
                                <div class="mb-3">
                                    <label>Autor:</label>
                                    <input type="text" class="form-control" name="autor"/>
                                </div>
                                <div class="mb-3">
                                    <label>Categoria:</label>
                                    <input type="text" class="form-control" name="categoria"/>
                                </div>
                                <div class="mb-3">
                                    <label>Costo:</label>
                                    <input type="number" class="form-control" name="costo"/>
                                </div>
                                <c:if test="${!empty(error)}">
                                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                        <strong>Error: </strong> ${error}
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                                </c:if>
                                <div class="text-center mt-4">
                                    <button class="btn btn-primary" type="submit" >Ingresar</button>
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