
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.SolicitudRevocacionBD;
import com.mycompany.bibliotecaweb.basededatos.UsuarioBD;
import com.mycompany.bibliotecaweb.tipos.SolicitudRevocacion;
import com.mycompany.bibliotecaweb.tipos.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;


@WebServlet(name = "SolicitudRevocacionServerManager", urlPatterns = {"/SolicitudRevocacionServer"})
public class SolicitudRevocacionServer extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");  
        String accion = request.getParameter("accion");
        SolicitudRevocacionBD solicitudRevocacionBD = new SolicitudRevocacionBD(conexion);
        
        if (accion.equals("listar")) {
            List<SolicitudRevocacion> solicitudes = solicitudRevocacionBD.listarSolicitudesRevocacion();
            request.setAttribute("solicitudes", solicitudes);
            request.getRequestDispatcher("/ListaDeSolicitudesRevocacion.jsp").forward(request, response);
        } else if (accion.equals("aceptar")) {
            int noUsuario = Integer.parseInt(request.getParameter("noUsuario"));
            solicitudRevocacionBD.aceptarORecharSolicitudRevocacion("Rechazado", noUsuario);
            UsuarioBD usuarioBD = new UsuarioBD(conexion);
            usuarioBD.suspenderORevocar(noUsuario, false);
            request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
        } else if (accion.equals("rechazar")) {
            int noUsuario = Integer.parseInt(request.getParameter("noUsuario"));
            solicitudRevocacionBD.aceptarORecharSolicitudRevocacion("Aceptado", noUsuario);
            request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        Usuario usuario = (Usuario) session.getAttribute("user");
        String descripcion = request.getParameter("descripcion");
        
        SolicitudRevocacionBD solicitudRevocacionBD = new SolicitudRevocacionBD(conexion);
        solicitudRevocacionBD.crearSolicitudRevocacion(usuario.getCodigo(), descripcion);
        request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
    }
}
