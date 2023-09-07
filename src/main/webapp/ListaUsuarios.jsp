<%-- 
    Document   : ListaUsuarios
    Created on : 5/09/2023, 12:07:20
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
            <div class="col-md-20">
                <div class="card-body">
                    <br>
                    <h1>Lista de Empleados</h1>
                    <form method="POST" action="${pageContext.request.contextPath}/BusquedaServer?accion=buscarRecepcionistasEspecificos">
                        <div class="row">
                            <div class="col">
                                <input class="form-control" placeholder="Número de Biblioteca" type="number" name="noBiblioteca"/><br>
                            </div> 
                            <div class="col">
                                <select class="form-select" aria-label="Default select example" name="tipo">
                                    <option value="2">Recepcionistas</option>
                                    <option value="3">Transportistas</option>
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
        <div class="container">
            <table id="listTareas" class="table table-striped table-sm" cellspacing="0" width="100%">
                <thead class="bg-dark text-white">
                    <tr>
                        <th>Codigo</th>
                        <th>No. Biblioteca</th>
                        <th>Nombre</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Estado</th>
                        <th>Editar</th>
                    </tr>
                </thead>               
                <tbody>
                    <c:forEach items="${usuarios}" var="usuario">
                        <tr>
                            <td>${usuario.codigo}</td>
                            <td>${usuario.biblioteca}</td>
                            <td>${usuario.nombre}</td>
                            <td>${usuario.username}</td>
                            <td>${usuario.email}</td>
                            <td>${usuario.suspendido ? 'Suspendido' : !usuario.suspendido ? 'Activo': 'Estado'}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/EditarUsuarioServer?accion=mostrarUsuario&codigo=${usuario.codigo}"
                                    class="btn btn-info btn-sm">Editar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="container">
            <a href="${pageContext.request.contextPath}/CrearUsuario.jsp" class="btn btn-info btn-sm">Crear Usuario</a>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
