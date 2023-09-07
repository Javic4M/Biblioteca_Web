
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.IncidenciaBD;
import com.mycompany.bibliotecaweb.basededatos.LibroBD;
import com.mycompany.bibliotecaweb.tipos.Libro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "EditarInformacionLibroServerManager", urlPatterns = {"/EditarInformacionLibro"})
public class EditarInformacionLibroServer extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        String accion = request.getParameter("accion");
        int isbn = Integer.parseInt(request.getParameter("isbn"));
                
        if (accion.equals("editar")) {
            LibroBD libroBD = new LibroBD(conexion);
            Libro libro = libroBD.obtenerLibroPrestamo("" + isbn);
            request.setAttribute("libro", libro);
            request.getRequestDispatcher("/InformacionLibro.jsp").forward(request, response);
        } else if (accion.equals("eliminar")) {
            LibroBD libroBD = new LibroBD(conexion);
            libroBD.eliminarLibro("" + isbn);
            IncidenciaBD incidenciaBD = new IncidenciaBD(conexion);
            incidenciaBD.incidenciaResuelta(isbn);
            //Mensaje a la Pantallla
            request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        String nombre = request.getParameter("nombre");
        String autor = request.getParameter("autor");
        String categoria = request.getParameter("categoria");
        int costo = Integer.parseInt(request.getParameter("costo"));

        LibroBD libroBD = new LibroBD(conexion);
        libroBD.actualizarLibro(isbn, nombre, autor, categoria, costo);
        request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
    }
}
