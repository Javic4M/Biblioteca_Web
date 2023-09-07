
package com.mycompany.bibliotecaweb.servers;

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

@WebServlet(name = "CrearUsuarioManager", urlPatterns = {"/CrearUsuarioServer"})
public class CrearUsuarioServer extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");        
        Usuario usuario = (Usuario) session.getAttribute("user");
        String nombre = request.getParameter("nombre");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
            
        if (usuario.getPuesto() == 2) {
            UsuarioBD usuarioBD = new UsuarioBD(conexion);

            if (usuarioBD.verificarUsername(username, 0, false)) {
                if (usuarioBD.crearUsuario(nombre, username, password, email, usuario.getBiblioteca())) {
                    request.setAttribute("informacion", "Usuario Creado");
                    request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Usuario no Creado");
                    request.getRequestDispatcher("/CrearUsuario.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "El UserName ya Existe, vuelva a Intentarlo");
                request.getRequestDispatcher("/CrearUsuario.jsp").forward(request, response);
            }
        } else if (usuario.getPuesto() == 1) {
            int tipo = Integer.parseInt(request.getParameter("tipo"));
            int noBiblioteca = Integer.parseInt(request.getParameter("noBiblioteca"));
            UsuarioBD usuarioBD = new UsuarioBD(conexion);

            if (usuarioBD.verificarUsername(username, 0, false)) {
                if (usuarioBD.crearUsuarioAdmin(tipo, noBiblioteca, nombre, username, password, email, usuario.getBiblioteca())) {
                    request.setAttribute("informacion", "Usuario Creado");
                    request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Usuario no Creado");
                    request.getRequestDispatcher("/CrearUsuario.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "El UserName ya Existe, vuelva a Intentarlo");
                request.getRequestDispatcher("/CrearUsuario.jsp").forward(request, response);
            }
        }
    }
}
