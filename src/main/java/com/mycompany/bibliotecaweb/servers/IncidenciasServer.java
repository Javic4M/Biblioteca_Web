
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.IncidenciaBD;
import com.mycompany.bibliotecaweb.basededatos.LibroBD;
import com.mycompany.bibliotecaweb.basededatos.PrestamoBD;
import com.mycompany.bibliotecaweb.basededatos.UsuarioBD;
import com.mycompany.bibliotecaweb.tipos.Incidencia;
import com.mycompany.bibliotecaweb.tipos.Libro;
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

@WebServlet(name = "IncidenciasServerManager", urlPatterns = {"/IncidenciasServer"})
public class IncidenciasServer extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        Usuario usuario = (Usuario) session.getAttribute("user");
        IncidenciaBD incidenciaBD = new IncidenciaBD(conexion);
        List<Incidencia> incidencias = incidenciaBD.obtenerIncidencias(usuario.getPuesto(), usuario.getCodigo());
        String accion = request.getParameter("accion");
        
        if ("listar".equals(accion)) {
            request.setAttribute("incidencias", incidencias);
            request.getRequestDispatcher("/ListaIncidencias.jsp").forward(request, response);
        } else if ("pagar".equals(accion)) {
            request.setAttribute("incidencias", incidencias);
            request.getRequestDispatcher("/MultasPorIncidencia.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        Usuario usuario = (Usuario) session.getAttribute("user");
        
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        int noUsuario = Integer.parseInt(request.getParameter("noUsuario"));
        LibroBD libroBD = new LibroBD(conexion);
        libroBD.marcarDisponibilidadDelLibro(isbn);
        Libro libro = libroBD.obtenerLibroPrestamo("" + isbn);
        String descripcion = request.getParameter("descripcion");
        IncidenciaBD incidenciaBD = new IncidenciaBD(conexion);
        incidenciaBD.crearIndicencia(noUsuario, descripcion, libro.getCosto(), isbn);
        PrestamoBD prestamoBD = new PrestamoBD(conexion);
        prestamoBD.finalizarPrestamo(isbn);
        UsuarioBD usuarioBD = new UsuarioBD(conexion);
        usuarioBD.suspenderORevocar(noUsuario, true);
        usuarioBD.suspenderORevocar(usuario.getCodigo(), true);
        usuario.suspenderUsuario(); 
        request.setAttribute("incidencias", "Incidencia Realizada");
        request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
    }
}
