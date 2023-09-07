
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.LibroBD;
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

@WebServlet(name = "BusquedaServerManager", urlPatterns = {"/BusquedaServer"})
public class BusquedaServer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        String accion = request.getParameter("accion");
        
        if (accion == null || accion.equals("listar")){
            LibroBD libroBD = new LibroBD(conexion);
            String columna = request.getParameter("tipo");
            String registro = request.getParameter("nombre");
            int noBiblioteca = Integer.parseInt(request.getParameter("noBiblioteca"));
            
            List<Libro> libros = libroBD.obtenerLibrosEspecificos(columna, registro, noBiblioteca);
            request.setAttribute("libros", libros);
            request.getRequestDispatcher("/Busqueda.jsp").forward(request, response);
        } else if (accion.equals("listarTodos")) {
            LibroBD libroBD = new LibroBD(conexion);
            List<Libro> libros = libroBD.listarLibros();
            request.setAttribute("libros", libros);
            request.getRequestDispatcher("/ListaDeLibros.jsp").forward(request, response);
        } else if (accion.equals("suspendidos")) {
            UsuarioBD usuarioBD = new UsuarioBD(conexion);
            List<Usuario> usuarios = usuarioBD.listarUsuariosSuspendidos();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/UsuariosSuspendidos.jsp").forward(request, response);
        }
    }
    
    //AQUI OBTENEMOS LOS PRESTAMOS DEL RECEPCIONISTA
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        String accion = request.getParameter("accion");
        
        if (accion.equals("buscarPrestamos")) {
            int codigoUsuario = Integer.parseInt(request.getParameter("codigo"));
            PrestamoBD prestamoBD = new PrestamoBD(conexion);
            List<Prestamo> prestamos = prestamoBD.listarPrestamo(codigoUsuario);
            request.setAttribute("prestamos", prestamos);
            request.getRequestDispatcher("/VisualizarPrestamos.jsp").forward(request, response);
        } else if (accion.equals("buscarLibrosEspecificos")) {
            int noBiblioteca = Integer.parseInt(request.getParameter("noBiblioteca"));
            LibroBD libroBD = new LibroBD(conexion);
            List<Libro> libros = libroBD.listarLibrosEspecificos(noBiblioteca);
            request.setAttribute("libros", libros);
            request.getRequestDispatcher("/ListaDeLibros.jsp").forward(request, response);
        } else if (accion.equals("buscarRecepcionistasEspecificos")) {
            int noBiblioteca = Integer.parseInt(request.getParameter("noBiblioteca"));
            int tipo = Integer.parseInt(request.getParameter("tipo"));
            System.out.println("Tipo: " + tipo);
            UsuarioBD usuarioBD = new UsuarioBD(conexion);
            List<Usuario> usuarios = usuarioBD.listaUsuariosPorBiblioteca(tipo, noBiblioteca);
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/ListaUsuarios.jsp").forward(request, response);
        }
    }
}
