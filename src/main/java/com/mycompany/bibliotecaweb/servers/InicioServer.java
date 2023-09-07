package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.data.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class InicioServer extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Conexion conexion = new Conexion();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(36000);
        session.setAttribute("conexion", conexion.obtenerConexion());
        response.sendRedirect("IniciarSesion.jsp");
    }
}