
package com.mycompany.bibliotecaweb.basededatos;

import com.mycompany.bibliotecaweb.tipos.Incidencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncidenciaBD {
    
    private Connection conexion;

    public IncidenciaBD(Connection conexion) {
        this.conexion = conexion;
    }
    
    public void crearIndicencia(int noUsuario, String descripcion, int costo, int isbn) {
        String query = "INSERT INTO incidencias (no_usuario, descripcion, admin_elimino, usuario_pago, costo, isbn) VALUES(?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement insert = conexion.prepareStatement(query);
            insert.setInt(1, noUsuario);
            insert.setString(2, descripcion);
            insert.setBoolean(3, false);
            insert.setBoolean(4, false);
            insert.setInt(5, costo);
            insert.setInt(6, isbn);
            insert.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear Incidencia: " + e);
        }
    }
    
    public List<Incidencia> obtenerIncidencias(int tipoDeUsuario, int noUsuario) {
        String query = null;
        
        if (tipoDeUsuario == 1) {
            query = "SELECT * FROM incidencias WHERE admin_elimino=false";
        } else {
            query = "SELECT * FROM incidencias WHERE usuario_pago=false AND no_usuario=?";
        }
        List<Incidencia> incidencias = new ArrayList<>();

        try {
            PreparedStatement select = conexion.prepareStatement(query);
            if (tipoDeUsuario == 4) {
                select.setInt(1, noUsuario);
            }
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                incidencias.add(new Incidencia(
                    resultset.getInt("no_usuario"),
                    resultset.getString("descripcion"),
                    resultset.getBoolean("admin_elimino"),
                    resultset.getBoolean("usuario_pago"),
                    resultset.getInt("costo"),
                    resultset.getInt("isbn"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return incidencias;
    }
    
    public Incidencia obtenerIncidencia(int noUsuario) {
        String query = "SELECT * FROM incidencias WHERE no_usuario=?";
        Incidencia incidencia = null;

        try {
            PreparedStatement select = conexion.prepareStatement(query);
            select.setInt(1, noUsuario);
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                incidencia = (new Incidencia(
                    resultset.getInt("no_usuario"),
                    resultset.getString("descripcion"),
                    resultset.getBoolean("admin_elimino"),
                    resultset.getBoolean("usuario_pago"),
                    resultset.getInt("costo"),
                    resultset.getInt("isbn"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return incidencia;
    }
    
    public void incidenciaResuelta(int isbn) {
        String query = "UPDATE incidencias SET admin_elimino=? WHERE isbn=?";
        
        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setBoolean(1, true);
            update.setInt(2, isbn);
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar tarea: " + e);
        }
    }
    
    public void multaPagada(int isbn) {
        String query = "UPDATE incidencias SET usuario_pago=? WHERE no_usuario=? AND isbn=?";
        
        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setBoolean(1, true);
            update.setInt(2, isbn);
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar tarea: " + e);
        }
    }
}
