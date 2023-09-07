<%-- 
    Document   : MovimientoSaldo
    Created on : 6/09/2023, 11:53:06
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movimiento Saldo</title>
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
                        <h1>Movimiento Saldo</h1>
                        <form method="POST" action="${pageContext.request.contextPath}/MovimientoSaldoServer">
                            <div class="row">
                                <div class="col">
                                    <select class="form-select" aria-label="Default select example" name="tipo">
                                        <option value="Ingreso">Ingresos</option>
                                        <option value="Egreso">Egresos</option>
                                    </select>
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
                        <th>No. Usuario</th>
                        <th>Saldo Anterior</th>
                        <th>Cantidad Movilizada</th>
                        <th>Movimiento</th>
                        <th>Saldo Nuevo</th>
                    </tr>
                </thead>               
                <tbody>
                    <c:forEach items="${movimientoSaldo}" var="movimientoSaldo">
                        <tr>
                            <td>${movimientoSaldo.no_usuario}</td>
                            <td>${movimientoSaldo.saldoAnterior}</td>
                            <td>${movimientoSaldo.cantidadMovilizada}</td>
                            <td>${movimientoSaldo.movimiento}</td>
                            <td>${movimientoSaldo.saldoNuevo}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="container">
            <a href="${pageContext.request.contextPath}/RecargarSaldo.jsp" class="btn btn-info btn-sm">Recargar</a>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
