
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.ParametrosBD;
import com.mycompany.bibliotecaweb.tipos.Parametro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "ParametrosUsuariosServerManager", urlPatterns = {"/ParametrosUsuariosServer"})
public class ParametrosUsuariosServer extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        String accion = request.getParameter("accion");
        
        if ("mostrar".equals(accion)) {
            ParametrosBD parametrosBD = new ParametrosBD(conexion);
            List<Parametro> parametros = parametrosBD.obtenerTodosLosParametros();
            request.setAttribute("parametros", parametros);
            request.getRequestDispatcher("Parametros.jsp").forward(request, response);
        } else if ("mostrarIndividual".equals(accion)) {
            String tipoUsuario = request.getParameter("tipo");
            ParametrosBD parametroBD = new ParametrosBD(conexion);
            Parametro parametro = parametroBD.obtenerParametros(tipoUsuario);
            request.setAttribute("parametro", parametro);
            request.getRequestDispatcher("ParametroIndividual.jsp").forward(request, response);
        }
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        String tipoDeUsuario = request.getParameter("tipoDeUsuario");
        int dias = Integer.parseInt(request.getParameter("dias"));
        int libro = Integer.parseInt(request.getParameter("libro"));
        int precio = Integer.parseInt(request.getParameter("precio"));
        int descuento = Integer.parseInt(request.getParameter("descuento"));
        int multa = Integer.parseInt(request.getParameter("multa"));
        int suspension = Integer.parseInt(request.getParameter("diasSuspension"));
        
        ParametrosBD parametroBD = new ParametrosBD(conexion);
        parametroBD.actualizarParametros(tipoDeUsuario, dias, libro, precio, descuento, multa, suspension);
        request.getRequestDispatcher("PantallaPrincipal.jsp").forward(request, response);
    }
}
