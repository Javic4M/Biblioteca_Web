<%-- 
    Document   : Incidencia
    Created on : 3/09/2023, 22:10:34
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
                            <h3 class="card-title text-center">Información Libro</h3>
                            <p>Al Marcar este libro con Incidencia seras suspendido y develaras pagar el costo total del libro</p>
                            <form method="POST" action="${pageContext.request.contextPath}/IncidenciasServer">
                                <div class="mb-3">
                                    <label>Codigo Usuario</label>
                                    <input class="form-control" name="noUsuario" value="${noUsuario}" aria-label="readonly input example" readonly/>
                                </div>
                                <div class="mb-3">
                                    <label>ISBN</label>
                                    <input class="form-control" name="isbn" value="${libro.ISBN}" aria-label="readonly input example" readonly/>
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
                                    <label>Categoria:</label>
                                    <input class="form-control" value="${libro.categoria}" aria-label="readonly input example" readonly/>
                                </div>
                                <div class="mb-3">
                                    <label>Costo:</label>
                                    <input class="form-control" value="${libro.costo}" aria-label="readonly input example" readonly/>
                                </div>   
                                <div class="mb-3">
                                    <label>Descripción:</label>
                                    <input type="text" class="form-control" name="descripcion"/>
                                </div>      
                                <div class="text-center mt-3">
                                    <button class="btn btn-primary" type="submit" >Registar Incidencia</button>
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
