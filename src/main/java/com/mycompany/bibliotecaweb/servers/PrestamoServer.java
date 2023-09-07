
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.LibroBD;
import com.mycompany.bibliotecaweb.basededatos.ParametrosBD;
import com.mycompany.bibliotecaweb.basededatos.PrestamoBD;
import com.mycompany.bibliotecaweb.basededatos.UsuarioBD;
import com.mycompany.bibliotecaweb.tipos.Libro;
import com.mycompany.bibliotecaweb.tipos.Prestamo;
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

@WebServlet(name = "PrestamoServerManager", urlPatterns = {"/PrestamoServer"})
public class PrestamoServer extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        String accion = request.getParameter("accion");
        
        if ("formularioP".equals(accion)) {
            String isbn = request.getParameter("codigo");
            LibroBD libroBD = new LibroBD(conexion);
            Libro libro = libroBD.obtenerLibroPrestamo(isbn);
            request.setAttribute("libro", libro);
            request.getRequestDispatcher("/Prestamo.jsp").forward(request, response);
        } else if ("formularioI".equals(accion)) {
            String isbn = request.getParameter("codigo");
            int noUsuario = Integer.parseInt(request.getParameter("usuario"));
            LibroBD libroBD = new LibroBD(conexion);
            Libro libro = libroBD.obtenerLibroPrestamo(isbn);
            request.setAttribute("noUsuario", noUsuario);
            request.setAttribute("libro", libro);
            request.getRequestDispatcher("/Incidencia.jsp").forward(request, response);
        } else if ("visualizarU".equals(accion)) {
            Usuario usuario = (Usuario) session.getAttribute("user");
            PrestamoBD prestamoBD = new PrestamoBD(conexion);
            List<Prestamo> prestamos = prestamoBD.listarPrestamo(usuario.getCodigo());
            request.setAttribute("prestamos", prestamos);
            request.getRequestDispatcher("/VisualizarPrestamos.jsp").forward(request, response);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("no_Libro");
        String idUsuario = request.getParameter("idUsuario");
        String fechaDevolucion = request.getParameter("fechaDevolucion");

        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        Usuario recepcionista = (Usuario) session.getAttribute("user");
        
        UsuarioBD usuarioBD = new UsuarioBD(conexion);
        LibroBD libroBD = new LibroBD(conexion);     
        
        Usuario cliente = usuarioBD.obtenerUsuario(idUsuario, "000", "prestamo");
        Libro libro = libroBD.obtenerLibroPrestamo(isbn);
        request.setAttribute("libro", libro);
        
        if (cliente != null) {
            ParametrosBD parametro = new ParametrosBD(conexion);
            
            if (parametro.verificarFecha(cliente.isPremiun(), fechaDevolucion)) {
                PrestamoBD prestamoBD = new PrestamoBD(conexion);
                prestamoBD.crearPrestamo(libro.getBiblioteca(), recepcionista.getCodigo(), Integer.parseInt(idUsuario), Integer.parseInt(isbn), fechaDevolucion);
                request.setAttribute("prestamoRealizado", "Prestamo Realizado");
                request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "En el rango de Dias");
                request.getRequestDispatcher("/Prestamo.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "El Usuario no Existe");
            request.getRequestDispatcher("/Prestamo.jsp").forward(request, response);
        }
    }
}
