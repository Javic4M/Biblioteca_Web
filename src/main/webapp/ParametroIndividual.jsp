<%-- 
    Document   : ParametroIndividual
    Created on : 3/09/2023, 18:10:23
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
                            <form method="POST" action="${pageContext.request.contextPath}/ParametrosUsuariosServer">
                                <div class="mb-3">
                                    <label>Tipo de Usuario</label>
                                    <input class="form-control" name="tipoDeUsuario" value="${parametro.tipoUsuario}" type="text" aria-label="readonly input example" readonly/>
                                </div>
                                <div class="mb-3">
                                    <label>Días Limite para el Prestamo de Libros</label>
                                    <input class="form-control" name="dias" value="${parametro.dias}" type="text"/>
                                </div>
                                <div class="mb-3">
                                    <label>Cantidad de Libros Maximo por Usuario</label>
                                    <input class="form-control" name="libro" value="${parametro.limLibros}" type="text"/>
                                </div>
                                <div class="mb-3">
                                    <label>Precio de la Suscripcion</label>
                                    <input class="form-control" name="precio" value="${parametro.suscripcionCosto}" type="number" name="idUsuario"/>
                                </div>
                                <div class="mb-3">
                                    <label>Descuento por Transporte en %</label>
                                    <input class="form-control" name="descuento" value="${parametro.descuento}" type="number" name="idUsuario"/>
                                </div>
                                <div class="mb-3">
                                    <label>Multa por Retraso de Entrega</label>
                                    <input class="form-control" name="multa" value="${parametro.multaPorRetraso}" type="number" name="idUsuario"/>
                                </div>
                                <div class="mb-3">
                                    <label>Días de Suspensión por Incidencia</label>
                                    <input class="form-control" name="diasSuspension" value="${parametro.diasSuspendido}" type="number" name="idUsuario"/>
                                </div>
                                <div class="text-center mt-3">
                                    <button class="btn btn-primary" type="submit">Guardar Cambios</button>
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
