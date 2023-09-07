
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.IncidenciaBD;
import com.mycompany.bibliotecaweb.basededatos.LibroBD;
import com.mycompany.bibliotecaweb.basededatos.PrestamoBD;
import com.mycompany.bibliotecaweb.basededatos.UsuarioBD;
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

@WebServlet(name = "FinalizarPrestamoServerManager", urlPatterns = {"/FinalizarPrestamoServer"})
public class FinalizarPrestamoServer extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        String accion = request.getParameter("accion");
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        LibroBD libroBD = new LibroBD(conexion);
        libroBD.marcarDisponibilidadDelLibro(isbn);
//        Libro libro = libroBD.obtenerLibroPrestamo("" + isbn);
        
        if (accion.equals("Incidencia")) {
//            String descripcion = request.getParameter("descripcion");
//            IncidenciaBD incidenciaBD = new IncidenciaBD(conexion);
//            incidenciaBD.crearIndicencia(isbn, descripcion, libro.getCosto());
//            PrestamoBD prestamoBD = new PrestamoBD(conexion);
//            prestamoBD.finalizarPrestamo(isbn);
//            UsuarioBD usuarioBD = new UsuarioBD(conexion);
//            usuarioBD.suspenderORevocar(cliente.getCodigo(), true);
//            cliente.suspenderUsuario();          
        } else if (accion.equals("Finalizar")) {
            PrestamoBD prestamoBD = new PrestamoBD(conexion);
            prestamoBD.finalizarPrestamo(isbn);
        }
        request.setAttribute("prestamoFinalizado", "Se finalizo Exitosamente");
        request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
    }
}
