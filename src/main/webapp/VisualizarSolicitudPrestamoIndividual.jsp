<%-- 
    Document   : VisualizarSolicitudPrestamoIndividual
    Created on : 2/09/2023, 22:52:46
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <form method="POST" action="${pageContext.request.contextPath}/SolicitudPrestamoUnitarioServer">
                                <div class="mb-3">
                                    <label>No. Solicitud</label>
                                    <input class="form-control" name="no_solicitud" value="${solicitudPrestamo.noSolicitudPrestamo}" aria-label="readonly input example" readonly/>
                                </div>
                                <div class="mb-3">
                                    <label>Biblioteca:</label>
                                    <input class="form-control" value="${solicitudPrestamo.noBiblioteca}" aria-label="readonly input example" readonly/>
                                </div>
                                <div class="mb-3">
                                    <label>Usuario</label>
                                    <input class="form-control" value="${solicitudPrestamo.noUsuario}" aria-label="readonly input example" readonly/>
                                </div>
                                <div class="mb-3">
                                    <label>ISBN</label>
                                    <input class="form-control" value="${solicitudPrestamo.isbn}" aria-label="readonly input example" readonly/>
                                </div>
                                <div class="mb-3">
                                    <label>Dias Solicitados</label>
                                    <input class="form-control" value="${solicitudPrestamo.dias}" aria-label="readonly input example" readonly/>
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
                                    <button class="btn btn-primary" type="submit">Crear Prestamo</button>
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

