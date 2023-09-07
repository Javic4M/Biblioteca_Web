
package com.mycompany.bibliotecaweb.basededatos;

import com.mycompany.bibliotecaweb.tipos.SolicitudPrestamo;
import com.mycompany.bibliotecaweb.tipos.SolicitudRevocacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolicitudRevocacionBD {
    
    private Connection conexion;

    public SolicitudRevocacionBD(Connection conexion) {
        this.conexion = conexion;
    }
    
    public boolean crearSolicitudRevocacion(int noUsuario, String descripcion) {
        String query = "INSERT INTO solicitudes_revocacion (no_usuario, descripcion, estado) VALUES(?, ?, ?)";
        
        try {
            PreparedStatement insert = conexion.prepareStatement(query);
            insert.setInt(1, noUsuario);
            insert.setString(2, descripcion);
            insert.setString(3, "Pendiente");
            insert.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear solicitud revocacion: " + e);
        }
        return false;
    }
    
    public List<SolicitudRevocacion> listarSolicitudesRevocacion() {
        String query = "SELECT * FROM solicitudes_revocacion WHERE estado=?";
        List<SolicitudRevocacion> solicitudes = new ArrayList<>();

        try { 
            PreparedStatement select = conexion.prepareStatement(query);
            select.setString(1, "Pendiente");
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                solicitudes.add(new SolicitudRevocacion(
                    resultset.getInt("no_usuario"),
                    resultset.getString("descripcion"),
                    resultset.getString("estado"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return solicitudes;  
    }
    
    public void aceptarORecharSolicitudRevocacion(String estado, int no_usuario) {
        String query = "UPDATE solicitudes_revocacion SET estado=? WHERE no_usuario=?";

        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setString(1, estado);
            update.setInt(2, no_usuario);
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);
        }
    }
}
