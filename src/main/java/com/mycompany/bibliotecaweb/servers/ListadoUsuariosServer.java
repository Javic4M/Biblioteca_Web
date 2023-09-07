
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
import java.util.List;

@WebServlet(name = "ListadoUsuariosServerManager", urlPatterns = {"/ListadoUsuariosServer"})
public class ListadoUsuariosServer extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        UsuarioBD usuarioBD = new UsuarioBD(conexion);
        List<Usuario> usuarios = usuarioBD.listaUsuariosPorPuesto(2);
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/ListaUsuarios.jsp").forward(request, response);
    }
}
