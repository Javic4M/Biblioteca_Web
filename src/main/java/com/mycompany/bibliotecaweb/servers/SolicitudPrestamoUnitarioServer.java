
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.ParametrosBD;
import com.mycompany.bibliotecaweb.basededatos.PrestamoBD;
import com.mycompany.bibliotecaweb.basededatos.SolicitudPrestamoBD;
import com.mycompany.bibliotecaweb.basededatos.UsuarioBD;
import com.mycompany.bibliotecaweb.tipos.SolicitudPrestamo;
import com.mycompany.bibliotecaweb.tipos.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "SolicitudPrestamoUnitarioServerManager", urlPatterns = {"/SolicitudPrestamoUnitarioServer"})
public class SolicitudPrestamoUnitarioServer extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int noSolicitud = Integer.parseInt(request.getParameter("no_solicitud"));
        String fechaDevolucion = request.getParameter("fechaDevolucion");
        
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        Usuario recepcionista = (Usuario) session.getAttribute("user");
        
        SolicitudPrestamoBD solicitudBD = new SolicitudPrestamoBD(conexion);
        SolicitudPrestamo solicitud = solicitudBD.obtenerSolicitudPrestamo(noSolicitud);
        solicitudBD.marcarFinalizacionDeLaSolicitud(noSolicitud);
        
        UsuarioBD usuarioBD = new UsuarioBD(conexion);
        Usuario cliente = usuarioBD.obtenerUsuario("" + solicitud.getNoUsuario(), "000", "prestamo");
        
        ParametrosBD parametro = new ParametrosBD(conexion);
            
        if (parametro.verificarFecha(cliente.isPremiun(), fechaDevolucion)) {
            PrestamoBD prestamoBD = new PrestamoBD(conexion);
            prestamoBD.crearPrestamo(solicitud.getNoBiblioteca(), recepcionista.getCodigo(), solicitud.getNoUsuario(), solicitud.getIsbn(), fechaDevolucion);
            request.setAttribute("prestamoRealizado", "Prestamo Realizado");
            request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
        } else {
            request.setAttribute("solicitud", solicitud);
            request.setAttribute("error", "En el rango de Dias");
            request.getRequestDispatcher("/Prestamo.jsp").forward(request, response);
        }
    }
}
