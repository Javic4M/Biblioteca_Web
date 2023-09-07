
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.SolicitudTransporteBD;
import com.mycompany.bibliotecaweb.tipos.SolicitudTransporte;
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

@WebServlet(name = "TransporteAUsuariosServerManager", urlPatterns = {"/TransporteAUsuariosServer"})
public class TransporteAUsuariosServer extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        Usuario usuario = (Usuario) session.getAttribute("user");
        SolicitudTransporteBD solicitudTransporteBD = new SolicitudTransporteBD(conexion);
        List<SolicitudTransporte> solicitudesTansportes = solicitudTransporteBD.listarSolicitudesTransporte(usuario.getCodigo());
        request.setAttribute("solicitudesTansportes", solicitudesTansportes);
        request.getRequestDispatcher("/SolicitudesTransportes.jsp").forward(request, response);
    }
}
