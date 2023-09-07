
package com.mycompany.bibliotecaweb.basededatos;

import com.mycompany.bibliotecaweb.tipos.Parametro;
import com.mycompany.bibliotecaweb.tipos.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestamoBD {
    
    private Connection conexion;

    public PrestamoBD(Connection conexion) {
        this.conexion = conexion;
    }
    
    public boolean crearPrestamo(int biblioteca, int recepcionista, int usuario, int isbn, String fecha) {
        String query = "INSERT INTO prestamo (no_biblioteca, no_recepcionista, no_usuario, isbn, fecha_entrega, estado_prestamo, pago, multa) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        LibroBD libroBD = new LibroBD(conexion);
        libroBD.marcarDisponibilidadDelLibro(isbn);
        
        try {
            PreparedStatement insert = conexion.prepareStatement(query);
            insert.setInt(1, biblioteca);
            insert.setInt(2, recepcionista);
            insert.setInt(3, usuario);
            insert.setInt(4, isbn);
            insert.setString(5, fecha);
            insert.setString(6, "Activo");
            insert.setString(7, "No tiene");
            insert.setInt(8, 0);
            insert.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        return false;
    }
    
    public Prestamo obtenerPrestamoAPagar(int numeroPrestamo) {
        String query = "SELECT * FROM prestamo WHERE no_prestamo=?";
        Prestamo prestamo = null;

        try { 
            PreparedStatement select = conexion.prepareStatement(query);
            select.setInt(1, numeroPrestamo);
            ResultSet resultset = select.executeQuery();
            
            if (resultset.next()) {
                prestamo = (new Prestamo(resultset.getInt("no_prestamo"),
                    resultset.getInt("no_biblioteca"),
                    resultset.getString("no_recepcionista"),
                    resultset.getString("no_usuario"),
                    resultset.getString("isbn"),
                    resultset.getString("fecha_entrega"),
                    resultset.getString("estado_prestamo"),
                    resultset.getString("pago"),
                    resultset.getString("multa"))
                );
            }
            System.out.println("");
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return prestamo;
    }
    
    public List<Prestamo> listarPrestamo(int idUsuario) {
        String query = "SELECT * FROM prestamo WHERE no_usuario=? AND estado_prestamo=?";
        List<Prestamo> prestamos = new ArrayList<>();

        try { 
            PreparedStatement select = conexion.prepareStatement(query);
            select.setInt(1, idUsuario);
            select.setString(2, "Activo");
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                prestamos.add(new Prestamo(resultset.getInt("no_prestamo"),
                    resultset.getInt("no_biblioteca"),
                    resultset.getString("no_recepcionista"),
                    resultset.getString("no_usuario"),
                    resultset.getString("isbn"),
                    resultset.getString("fecha_entrega"),
                    resultset.getString("estado_prestamo"),
                    resultset.getString("pago"),
                    resultset.getString("multa"))
                );
            }
            System.out.println("");
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return prestamos;  
    }
    
    public List<Prestamo> listarPrestamoSinMulta(int idUsuario) {
        String query = "SELECT * FROM prestamo WHERE no_usuario=? AND pago=?";
        List<Prestamo> prestamos = new ArrayList<>();

        try { 
            PreparedStatement select = conexion.prepareStatement(query);
            select.setInt(1, idUsuario);
            select.setString(2, "No tiene");
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                prestamos.add(new Prestamo(resultset.getInt("no_prestamo"),
                    resultset.getInt("no_biblioteca"),
                    resultset.getString("no_recepcionista"),
                    resultset.getString("no_usuario"),
                    resultset.getString("isbn"),
                    resultset.getString("fecha_entrega"),
                    resultset.getString("estado_prestamo"),
                    resultset.getString("pago"),
                    resultset.getString("multa"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return prestamos;  
    }
    
    public void marcarMulta(int noPrestamo, boolean esPremiun) {
        String query = "UPDATE prestamo SET pago=?, multa=? WHERE no_prestamo = ?";
        ParametrosBD parametrosBD = new ParametrosBD(conexion);
        String tipoDeUsuario = null;
        
        if (esPremiun) {
            tipoDeUsuario = "premiun";
        } else {
            tipoDeUsuario = "normal";
        }
        Parametro parametro = parametrosBD.obtenerParametros(tipoDeUsuario);
                
        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setString(1, "Pendiente");
             update.setInt(2, parametro.getMultaPorRetraso());
            update.setInt(3, noPrestamo);
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar tarea: " + e);
        }
    }
    
    public List<Prestamo> obtenerMultas(int codigo) {
        String query = "SELECT * FROM prestamo WHERE no_usuario=? AND pago=?";
        List<Prestamo> prestamos = new ArrayList<>();

        try { 
            PreparedStatement select = conexion.prepareStatement(query);
            select.setInt(1, codigo); 
            select.setString(2, "Pendiente");
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                prestamos.add(new Prestamo(resultset.getInt("no_prestamo"),
                    resultset.getInt("no_biblioteca"),
                    resultset.getString("no_recepcionista"),
                    resultset.getString("no_usuario"),
                    resultset.getString("isbn"),
                    resultset.getString("fecha_entrega"),
                    resultset.getString("estado_prestamo"),
                    resultset.getString("pago"),
                    resultset.getString("multa"))
                );
            }
            System.out.println("");
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return prestamos;
    }
    
    public void finalizarPrestamo(int isbn) {
        String query = "UPDATE prestamo SET estado_prestamo=? WHERE isbn=?";
        
        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setString(1, "Finalizado");
            update.setInt(2, isbn);
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar tarea: " + e);
        }
    }
    
    public void multaPagada(int isbn) {
        String query = "UPDATE prestamo SET pago=? WHERE isbn=?";
        
        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setString(1, "Realizado");
            update.setInt(2, isbn);
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar tarea: " + e);
        }
    }
}
