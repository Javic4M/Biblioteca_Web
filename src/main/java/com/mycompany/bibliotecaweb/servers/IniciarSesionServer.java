
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.ParametrosBD;
import com.mycompany.bibliotecaweb.basededatos.UsuarioBD;
import com.mycompany.bibliotecaweb.tipos.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "IniciarSesionServerManager", urlPatterns = {"/IniciarSesion"})
public class IniciarSesionServer extends HttpServlet {
    
    private Usuario usuarioLogin;
    private UsuarioBD usuarioDB;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        usuarioDB = new UsuarioBD(conexion);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (validarUsuario(username, password)) {
            if (!usuarioLogin.isSuspendido()) {
                session.setAttribute("user", usuarioLogin);
                ParametrosBD parametrosBD = new ParametrosBD(conexion);
                parametrosBD.verificarPrestamos(usuarioLogin, conexion);
                response.sendRedirect("PantallaPrincipal.jsp");
            } else {
                if (usuarioLogin.getPuesto() == 2) {
                    request.setAttribute("error", "Estas Suspendido");
                    request.getRequestDispatcher("IniciarSesion.jsp").forward(request, response);
                } else {
                    session.setAttribute("user", usuarioLogin);
                    ParametrosBD parametrosBD = new ParametrosBD(conexion);
                    parametrosBD.verificarPrestamos(usuarioLogin, conexion);
                    response.sendRedirect("PantallaPrincipal.jsp");
                }
            }
        } else {
            request.setAttribute("error", "Username o Contraseña Incorrecta");
            request.getRequestDispatcher("IniciarSesion.jsp").forward(request, response);
        }
    }

    public boolean validarUsuario(String username, String password) {
        Usuario optionalUsuario = usuarioDB.obtenerUsuario(username, password, "validar");
        if (optionalUsuario == null) { 
            return false;
        }
        usuarioLogin = optionalUsuario;
        return true;
    }
}
