
package com.mycompany.bibliotecaweb.basededatos;

import com.mycompany.bibliotecaweb.tipos.SolicitudTransporte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolicitudTransporteBD {
    
    private Connection conexion;

    public SolicitudTransporteBD(Connection conexion) {
        this.conexion = conexion;
    }
    
    public boolean crearSolicitudTransportista(int transportista, int usuario, int isbn, int dias, String fecha, String direccion) {
        String query = "INSERT INTO solicitudes_transportes (no_transportista, no_usuario, isbn, dias, fecha_emision, estado, direccion) VALUES(?, ?, ?, ?, ?, ?, ?)";
        LibroBD libroBD = new LibroBD(conexion);
        libroBD.marcarDisponibilidadDelLibro(isbn);
        
        try {
            PreparedStatement insert = conexion.prepareStatement(query);
            insert.setInt(1, transportista);
            insert.setInt(2, usuario);
            insert.setInt(3, isbn);
            insert.setInt(4, dias);
            insert.setString(5, fecha);
            insert.setString(6, "Pendiente");
            insert.setString(7, direccion);
            insert.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        return false;
    }
    
    public List<SolicitudTransporte> listarSolicitudesTransporte(int no_usuario) {
        String query = "SELECT * FROM solicitudes_transportes WHERE no_transportista=? AND estado=?";
        List<SolicitudTransporte> solicitudes = new ArrayList<>();
        
        try { 
            PreparedStatement select = conexion.prepareStatement(query);
            select.setInt(1, no_usuario);
            select.setString(2, "Pendiente");
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                solicitudes.add(new SolicitudTransporte(
                    resultset.getInt("no_transporte"),
                    resultset.getInt("no_transportista"),
                    resultset.getInt("no_usuario"),
                    resultset.getInt("isbn"),
                    resultset.getInt("dias"),
                    resultset.getString("fecha_emision"),
                    resultset.getString("estado"),
                    resultset.getString("direccion"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return solicitudes;
    }
}
