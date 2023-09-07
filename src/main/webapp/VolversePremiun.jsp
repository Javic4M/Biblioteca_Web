<%-- 
    Document   : VolversePremiun
    Created on : 3/09/2023, 01:55:47
    Author     : DELL
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>RecargarSaldo</title>

    <!--CSS-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title text-center">Premiun</h3><br>
                    <form method="POST" action="${pageContext.request.contextPath}/EditarUsuarioServer?accion=serPremiun">
                        <div class="mb-3">
                            <label>Ingrese la Cantidad de Dinero a Depositar:</label>
                            <br>
                            <p>Al volverse un usuario <span style = "color:red"> Premiun </span>, obtienes ciertas ventajas como lo son descuentos en transporte, solicitudes automaticas de prestamo y más dias de prestamos y más espacio de libros</p>
                        </div>
                        <c:if test="${!empty(error)}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <strong>Error:</strong> ${error}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        <div class="text-center d-grid gap-2">
                            <button type="submit" class="btn btn-primary ">Ser Premiun</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
                        
<!--JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"crossorigin="anonymous"></script>
</body>
</html>
