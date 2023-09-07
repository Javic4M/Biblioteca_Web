
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.IncidenciaBD;
import com.mycompany.bibliotecaweb.basededatos.MovimientoSaldoBD;
import com.mycompany.bibliotecaweb.basededatos.PrestamoBD;
import com.mycompany.bibliotecaweb.basededatos.UsuarioBD;
import com.mycompany.bibliotecaweb.tipos.Incidencia;
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

@WebServlet(name = "MultasServerManager", urlPatterns = {"/MultasServer"})
public class MultasServer extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        Usuario usuario = (Usuario) session.getAttribute("user");
        String accion = request.getParameter("accion");
        
        if (accion == null) {
            PrestamoBD prestamosBD = new PrestamoBD(conexion);
            List<Prestamo> prestamos = prestamosBD.obtenerMultas(usuario.getCodigo());
            request.setAttribute("prestamos", prestamos);
            request.getRequestDispatcher("/MultasPorTardanza.jsp").forward(request, response);
        } else if ("PagarRetraso".equals(accion)) {
            int noPrestamo = Integer.parseInt(request.getParameter("noPrestamo"));
            PrestamoBD prestamoBD = new PrestamoBD(conexion);
            Prestamo prestamo = prestamoBD.obtenerPrestamoAPagar(noPrestamo);
            
            if (usuario.getSaldo() >= prestamo.getMulta()) {
                UsuarioBD usuarioBD = new UsuarioBD(conexion);
                usuarioBD.recargarSaldo(usuario.getCodigo(), usuario.getSaldo(), -prestamo.getMulta());
                usuario.setSaldo(-prestamo.getMulta());
                MovimientoSaldoBD movimientoSaldoBD = new MovimientoSaldoBD(conexion);
                movimientoSaldoBD.ingresarMovimientoSaldo(usuario.getCodigo(), usuario.getSaldo(), prestamo.getMulta(), "Egreso", (usuario.getSaldo() - prestamo.getMulta()));
                prestamoBD.multaPagada(prestamo.getISBN());
                request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
            } else {
                
            }
        } else if ("PagarIncidencia".equals(accion)) {
            int isbn = Integer.parseInt(request.getParameter("isbn"));
            IncidenciaBD incidenciaBD = new IncidenciaBD(conexion);
            Incidencia incidencia = incidenciaBD.obtenerIncidencia(usuario.getCodigo());
            
            if (usuario.getSaldo() >= incidencia.getCosto()) {
                UsuarioBD usuarioBD = new UsuarioBD(conexion);
                usuarioBD.recargarSaldo(usuario.getCodigo(), usuario.getSaldo(), -incidencia.getCosto());
                usuario.setSaldo(-incidencia.getCosto());
                MovimientoSaldoBD movimientoSaldoBD = new MovimientoSaldoBD(conexion);
                movimientoSaldoBD.ingresarMovimientoSaldo(usuario.getCodigo(), usuario.getSaldo(), incidencia.getCosto(), "Egreso", (usuario.getSaldo() - incidencia.getCosto()));
                incidenciaBD.multaPagada(isbn);
                request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
            } else {
                
            }
        }
    }
}
