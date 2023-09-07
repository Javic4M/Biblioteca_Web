
package com.mycompany.bibliotecaweb.servers;

import com.mycompany.bibliotecaweb.basededatos.MovimientoSaldoBD;
import com.mycompany.bibliotecaweb.basededatos.ParametrosBD;
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

@WebServlet(name = "EditarUsuarioServerManager", urlPatterns = {"/EditarUsuarioServer"})
public class EditarUsuarioServer extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");    
        String accion = request.getParameter("accion");
        
        if ("mostrarUsuario".equals(accion)) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            UsuarioBD usuarioBD = new UsuarioBD(conexion);
            Usuario usuarioAActualizar = usuarioBD.obtenerUsuario(""+codigo, "000", "prestamo");
            request.setAttribute("usuario", usuarioAActualizar);
            request.getRequestDispatcher("/EditarUsuarios.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");    
        String accion = request.getParameter("accion");
        
        if (accion == null) {
            Usuario usuario = (Usuario) session.getAttribute("user");
            int saldo = Integer.parseInt(request.getParameter("saldo"));
            
            if (saldo > 0) {
                UsuarioBD usuarioBD = new UsuarioBD(conexion);
                usuarioBD.recargarSaldo(usuario.getCodigo(), usuario.getSaldo(), saldo);
                usuario.setSaldo(saldo);
                MovimientoSaldoBD movimientoSaldoBD = new MovimientoSaldoBD(conexion);
                movimientoSaldoBD.ingresarMovimientoSaldo(usuario.getCodigo(), usuario.getSaldo(), saldo, "Ingreso", (usuario.getSaldo() + saldo));
                request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Debes Ingresar una Cantidad de Dinero");
                request.getRequestDispatcher("/RecargarSaldo.jsp").forward(request, response);
            }
        } else if ("actualizarRecepcionista".equals(accion)) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            String username = request.getParameter("username");
            UsuarioBD usuarioBD = new UsuarioBD(conexion);
            Usuario recepcionista = usuarioBD.obtenerUsuario("" + codigo, "000", "prestamo");
            
            if (usuarioBD.verificarUsername(username, recepcionista.getCodigo(), true)) {
                String nombre = request.getParameter("nombre");
                String email = request.getParameter("email");
                usuarioBD.actualizarUsuario(recepcionista.getCodigo(), nombre, username, recepcionista.getPassword(), email);
                String tipo = request.getParameter("tipo");
                boolean estado;
                if ("true".equals(tipo)) {
                    estado = true;
                } else {
                    estado = false;
                }
                usuarioBD.suspenderORevocar(recepcionista.getCodigo(), estado);
                request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
            }
        } else if ("serPremiun".equals(accion)) {
            Usuario usuario = (Usuario) session.getAttribute("user");
            ParametrosBD parametroBD = new ParametrosBD(conexion);
            int costo = parametroBD.verirficarCostoDeSuscripcion();

            if (usuario.getSaldo() > costo) {
                UsuarioBD usuarioBD = new UsuarioBD(conexion);
                usuarioBD.serPremiun(usuario.getCodigo());
                usuarioBD.recargarSaldo(usuario.getCodigo(), usuario.getSaldo(), -costo);
                usuario.setSaldo(-costo);
                MovimientoSaldoBD movimientoSaldoBD = new MovimientoSaldoBD(conexion);
                movimientoSaldoBD.ingresarMovimientoSaldo(usuario.getCodigo(), usuario.getSaldo(), costo, "Egreso", (usuario.getSaldo() - costo));
                usuario.setPremiun();
                request.setAttribute("premiun", "Felicidades ahora eres un Usurio Premiun");
                request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "No tienes dinero has una Recarga");
                request.getRequestDispatcher("/VolversePremiun.jsp").forward(request, response);
            }
        } else if ("editar".equals(accion)) {
            Usuario usuario = (Usuario) session.getAttribute("user");
            String nombre = request.getParameter("nombre");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UsuarioBD usuarioBD = new UsuarioBD(conexion);          
            usuario.setNombre(nombre);
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setEmail(email);
            if (usuarioBD.verificarUsername(username, usuario.getCodigo(), true)) {
                usuarioBD.actualizarUsuario(usuario.getCodigo(), nombre, username, password, email);
                request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/PantallaPrincipal.jsp").forward(request, response);
            }
        }
    }
}