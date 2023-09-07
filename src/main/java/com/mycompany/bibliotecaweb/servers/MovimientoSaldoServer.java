
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.MovimientoSaldoBD;
import com.mycompany.bibliotecaweb.tipos.MovimientoSaldo;
import com.mycompany.bibliotecaweb.tipos.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MovimientoSaldoServerManager", urlPatterns = {"/MovimientoSaldoServer"})
public class MovimientoSaldoServer extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        Usuario usuario = (Usuario) session.getAttribute("user");
        MovimientoSaldoBD movimientoSaldoBD = new MovimientoSaldoBD(conexion);
        List<MovimientoSaldo> movimientoSaldo = movimientoSaldoBD.listarMovimientoIngresoYDebito(usuario.getCodigo(), "Ingreso");
        request.setAttribute("movimientoSaldo", movimientoSaldo);
        request.getRequestDispatcher("/MovimientoSaldo.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        Usuario usuario = (Usuario) session.getAttribute("user");
        String tipo = request.getParameter("tipo");
        
        MovimientoSaldoBD movimientoSaldoBD = new MovimientoSaldoBD(conexion);
        List<MovimientoSaldo> movimientoSaldo = new ArrayList<>();
                
        if (tipo.equals("Ingreso")) {
            movimientoSaldo = movimientoSaldoBD.listarMovimientoIngresoYDebito(usuario.getCodigo(), "Ingreso");
        } else if (tipo.equals("Egreso")) {
            movimientoSaldo = movimientoSaldoBD.listarMovimientoIngresoYDebito(usuario.getCodigo(), "Egreso");
        }
        request.setAttribute("movimientoSaldo", movimientoSaldo);
        request.getRequestDispatcher("/MovimientoSaldo.jsp").forward(request, response);
    }
}
