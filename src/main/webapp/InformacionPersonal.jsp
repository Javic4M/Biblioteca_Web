<%-- 
    Document   : InformacionPersonal
    Created on : 1/09/2023, 09:35:30
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
        <jsp:include page="/includes/header.jsp"/>
        <br>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title text-center">Información</h3>
                            <form method="POST" id="form-login" action="${pageContext.request.contextPath}/EditarUsuarioServer?accion=editar">
                                <div class="mb-3">
                                    <label>Codigo:</label>
                                    <input class="form-control" value="${user.codigo}" aria-label="readonly input example" readonly/>
                                </div>
                                <div class="mb-3">
                                    <label>Nombre:</label>
                                    <input class="form-control" value="${user.nombre}" name="nombre"/>
                                </div>
                                <div class="mb-3">
                                    <label>Username:</label>
                                    <input class="form-control" value="${user.username}" name="username"/>
                                </div>
                                <div class="mb-3">
                                    <label>Email:</label>
                                    <input class="form-control" value="${user.email}" name="email"/>
                                </div>
                                <div class="mb-3">
                                    <label>Email:</label>
                                    <input class="form-control" value="${user.password}" name="password"/>
                                </div>
                                <div class="mb-3">
                                    <label>Suspendido:</label>
                                    <input class="form-control" name="premiun" value="${user.suspendido}" aria-label="readonly input example" readonly/>
                                </div>
                                <div class="mb-3">
                                    <label>Premiun:</label>
                                    <input class="form-control" name="suspendido" value="${user.premiun}" aria-label="readonly input example" readonly/>
                                    <c:if test="${!user.premiun}">
                                        <a href="${pageContext.request.contextPath}/VolversePremiun.jsp"
                                            class="btn btn-info btn-sm">Ser Premiun</a>
                                    </c:if>
                                </div>
                                <div class="mb-3">
                                    <label>Saldo:</label>
                                    <input class="form-control" name="saldo" value="${user.saldo}" aria-label="readonly input example" readonly/>
                                    <a href="${pageContext.request.contextPath}/RecargarSaldo.jsp"
                                                class="btn btn-info btn-sm">Recargar</a>
                                </div>
                                <c:if test="${!empty(error)}">
                                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                        <strong>Error: </strong> ${error}
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                                </c:if>
                                <br>
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
