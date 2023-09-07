
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.LibroBD;
import com.mycompany.bibliotecaweb.basededatos.ParametrosBD;
import com.mycompany.bibliotecaweb.basededatos.SolicitudPrestamoBD;
import com.mycompany.bibliotecaweb.basededatos.SolicitudTransporteBD;
import com.mycompany.bibliotecaweb.basededatos.UsuarioBD;
import com.mycompany.bibliotecaweb.tipos.Libro;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SolicitudPrestamoServerManager", urlPatterns = {"/SolicitudPrestamoServer"})
public class SolicitudPrestamoServer extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        Usuario recepcionista = (Usuario) session.getAttribute("user");
        String accion = request.getParameter("accion");
        
        if ("formulario".equals(accion)) {
            String isbn = request.getParameter("codigo");
            LibroBD libroBD = new LibroBD(conexion);
            Libro libro = libroBD.obtenerLibroPrestamo(isbn);
            request.setAttribute("libro", libro);
            request.getRequestDispatcher("/SolicitudPrestamo.jsp").forward(request, response);
        } else if ("visualizar".equals(accion)) {
            SolicitudPrestamoBD solicitudPrestamoBD = new SolicitudPrestamoBD(conexion);
            List<SolicitudPrestamo> solicitudes = solicitudPrestamoBD.listarSolicitudesPrestamo(recepcionista.getBiblioteca());
            request.setAttribute("solicitudes", solicitudes);
            request.getRequestDispatcher("/VisualizarSolicitudesDePrestamo.jsp").forward(request, response);
        } else if ("aceptar".equals(accion)) {
            int noSolicitud = Integer.parseInt(request.getParameter("codigo"));

            SolicitudPrestamoBD solicitudPrestamoBD = new SolicitudPrestamoBD(conexion);
            SolicitudPrestamo solicitudPrestamo = solicitudPrestamoBD.obtenerSolicitudPrestamo(noSolicitud);
            
            request.setAttribute("solicitudPrestamo", solicitudPrestamo);
            request.getRequestDispatcher("/VisualizarSolicitudPrestamoIndividual.jsp").forward(request, response);
        } else if ("rechazar".equals(accion)) {
            int noSolicitud = Integer.parseInt(request.getParameter("noSolicitud"));
            SolicitudPrestamoBD solicitudPrestamoBD = new SolicitudPrestamoBD(conexion);
            solicitudPrestamoBD.obtenerSolicitudPrestamo(noSolicitud);
            request.setAttribute("prestamo", "No se Realizo el prestamo");
            request.getRequestDispatcher("/VisualizarSolicitudesDePrestamo.jsp").forward(request, response);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("no_Libro");
        int dias = Integer.parseInt(request.getParameter("dias"));
        String tipoDeEntrega = request.getParameter("tipo");
        String direccion = request.getParameter("direccion");
        
        if ("".equals(direccion) || "Direccion".equals(direccion)) {
            direccion = "----------";
        }
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        Usuario usuario = (Usuario) session.getAttribute("user");
        
        LibroBD libroBD = new LibroBD(conexion);
        Libro libro = libroBD.obtenerLibroPrestamo(isbn);
        request.setAttribute("libro", libro);
        
        ParametrosBD parametro = new ParametrosBD(conexion);

        if (parametro.verificarDias(usuario.isPremiun(), dias)) {
            Date todayDate = new Date();
            
            SimpleDateFormat dia = new SimpleDateFormat("dd");
            SimpleDateFormat mes = new SimpleDateFormat("MM");
            SimpleDateFormat ano = new SimpleDateFormat("yyyy");

            String fDia = dia.format(todayDate);
            String fMes = mes.format(todayDate);
            String faño = ano.format(todayDate);

            String fechaActual = faño + "-" + fMes + "-" + fDia;
            
            if ("Biblioteca".equals(tipoDeEntrega)) {
                SolicitudPrestamoBD solicitudPrestamoBD = new SolicitudPrestamoBD(conexion);
                solicitudPrestamoBD.crearSolicitudPrestamo(libro.getBiblioteca(), usuario.getCodigo(), Integer.parseInt(isbn), dias, fechaActual, tipoDeEntrega, direccion);
            } else if ("Domicilio".equals(tipoDeEntrega)) {
                SolicitudTransporteBD solicitudTransporteBD = new SolicitudTransporteBD(conexion);
                UsuarioBD usuarioBD = new UsuarioBD(conexion);
                int noTransportista = usuarioBD.obtenerTransportista();
                solicitudTransporteBD.crearSolicitudTransportista(noTransportista, usuario.getCodigo(), Integer.parseInt(isbn), dias, fechaActual, direccion);
            }
            
            libroBD.marcarDisponibilidadDelLibro(libro.getISBN());
            request.setAttribute("solicitudRealizada", "Solicitud Reaizada");
            request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "En el rango de Dias");
            request.getRequestDispatcher("/Prestamo.jsp").forward(request, response);
        }
    }
}
