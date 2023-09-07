
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.LibroBD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "NuevoLibroServerManager", urlPatterns = {"/NuevoLibroServer"})
public class NuevoLibroServer extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        int noBiblioteca = Integer.parseInt(request.getParameter("noBiblioteca"));
        String nombre = request.getParameter("nombre");
        String autor = request.getParameter("autor");
        String categoria = request.getParameter("categoria");
        int costo = Integer.parseInt(request.getParameter("costo"));
        LibroBD libroBD = new LibroBD(conexion);
        
        if (libroBD.ingresarNuevoLibro(isbn, noBiblioteca, nombre, autor, categoria, costo)) {
            request.setAttribute("LibroNuevo", "Libro Ingresado Exitosamente");
        } else {
            request.setAttribute("ErrorIngresoLibro", "Hubo un Error al Ingresar el Libro");
        }
        request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
    }
}
