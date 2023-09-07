
package com.mycompany.bibliotecaweb.basededatos;

import com.mycompany.bibliotecaweb.tipos.SolicitudPrestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolicitudPrestamoBD {
    
    private Connection conexion;

    public SolicitudPrestamoBD(Connection conexion) {
        this.conexion = conexion;
    }
    
    public boolean crearSolicitudPrestamo(int biblioteca, int usuario, int isbn, int dias, String fecha, String tipoDeEntrega, String direccion) {
        String query = "INSERT INTO solicitudes_prestamos (no_biblioteca, no_usuario, isbn, dias, fecha_emision, estado_solicitud) VALUES(?, ?, ?, ?, ?, ?)";
        LibroBD libroBD = new LibroBD(conexion);
        libroBD.marcarDisponibilidadDelLibro(isbn);
        
        try {
            PreparedStatement insert = conexion.prepareStatement(query);
            insert.setInt(1, biblioteca);
            insert.setInt(2, usuario);
            insert.setInt(3, isbn);
            insert.setInt(4, dias);
            insert.setString(5, fecha);
            insert.setString(6, "Pendiente");
            insert.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        return false;
    }
    
    public List<SolicitudPrestamo> listarSolicitudesPrestamo(int noDeBiblioteca) {
        String query = "SELECT * FROM solicitudes_prestamos WHERE no_biblioteca=? AND estado_solicitud=?";
        List<SolicitudPrestamo> solicitudes = new ArrayList<>();

        try { 
            PreparedStatement select = conexion.prepareStatement(query);
            select.setInt(1, noDeBiblioteca);
            select.setString(2, "Pendiente");
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                solicitudes.add(new SolicitudPrestamo(
                    resultset.getInt("no_solicitud"),
                    resultset.getInt("no_biblioteca"),
                    resultset.getInt("no_usuario"),
                    resultset.getInt("isbn"),
                    resultset.getInt("dias"),
                    resultset.getString("fecha_emision"),
                    resultset.getString("estado_solicitud"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return solicitudes;  
    }
    
    public SolicitudPrestamo obtenerSolicitudPrestamo(int noSolicitud) {
        String query = "SELECT * FROM solicitudes_prestamos WHERE no_solicitud=?";
        SolicitudPrestamo solicitud = null;

        try { 
            PreparedStatement select = conexion.prepareStatement(query);
            select.setInt(1, noSolicitud);
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                solicitud = (new SolicitudPrestamo(
                    resultset.getInt("no_solicitud"),
                    resultset.getInt("no_biblioteca"),
                    resultset.getInt("no_usuario"),
                    resultset.getInt("isbn"),
                    resultset.getInt("dias"),
                    resultset.getString("fecha_emision"),
                    resultset.getString("estado_solicitud"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return solicitud;
    }
    
    public void marcarFinalizacionDeLaSolicitud(int noSolicitud) {
        String query = "UPDATE solicitudes_prestamos SET estado_solicitud = ? WHERE no_solicitud = ?";
        
        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setString(1, "Finalizado");
            update.setInt(2, noSolicitud);
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar tarea: " + e);
        }
    }
}
