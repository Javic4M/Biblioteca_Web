<%-- 
    Document   : Encabezado
    Created on : 24/08/2023, 20:33:07
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<nav class="navbar navbar-expand navbar-dark bg-dark" aria-label="Second navbar example">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/PantallaPrincipal.jsp">SITIO WEB</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <c:if test="${user.puesto == 1}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/BusquedaServer?accion=listarTodos">Listado de Libros</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 1}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/ParametrosUsuariosServer?accion=mostrar">Parametros</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 1}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/IncidenciasServer?accion=listar">Incidencias</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 1}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/ListadoUsuariosServer">Recepcionista y Transportistas</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 1}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/SolicitudRevocacionServer?accion=listar">Solicitudes Revocacion Suspensión</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 2}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/CrearUsuario.jsp">Crear Usuario</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 2}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/SolicitudPrestamoServer?accion=visualizar">Solicitudes de Usuarios</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 2}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/FinalizarPrestamo.jsp">Prestamos</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 2}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/BusquedaServer?accion=suspendidos">Usuarios Suspendidos</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 2}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/BusquedaServer?accion=suspendidos">Top de Incidencias</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 3}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/TransporteAUsuariosServer">Transportes a Usuario</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 4}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/PrestamoServer?accion=visualizarU">Prestamos Activos</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 4}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/InformacionPersonal.jsp">Información Personal</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 4}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/MultasServer">Multas por Retraso</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 4}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/IncidenciasServer?accion=pagar">Multas por Incidencia</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 4}">
            <div class="collapse navbar-collapse" id="navbarsExample02">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/MovimientoSaldoServer">Movimiento Saldo</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${user.puesto == 4}">
            <c:if test="${user.suspendido}">
                <div class="collapse navbar-collapse" id="navbarsExample02">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/SolicitudRevocacion.jsp">Solicitud Revocacion Suspensión</a>
                        </li>
                    </ul>
                </div>
            </c:if>
        </c:if>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNav2">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        ${user.nombre}
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/CerrarSesionServer">Cerrar sesion</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>   
</nav>