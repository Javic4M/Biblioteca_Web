
package com.mycompany.bibliotecaweb.basededatos;

import com.mycompany.bibliotecaweb.tipos.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroBD {
    
    private Connection conexion;

    public LibroBD(Connection conexion) {
        this.conexion = conexion;
    }
    
    public void marcarDisponibilidadDelLibro(int isbn) {
        Libro libro = obtenerLibroPrestamo("" + isbn);
        boolean estado;
        
        if (libro.isDisponible()) {
            estado = false;
        } else {
            estado = true;
        }
        
        String query = "UPDATE libro SET disponible = ? WHERE isbn = ?";
        
        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setBoolean(1, estado);
            update.setInt(2, libro.getISBN());
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar tarea: " + e);
        }
    }
    
    public boolean ingresarNuevoLibro(int isbn, int noBiblioteca, String nombre, String autor, String categoria, int costo) {
        String query = "INSERT INTO libro (isbn, no_biblioteca, nombre, autor, categoria, costo, disponible) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement insert = conexion.prepareStatement(query);
            insert.setInt(1, isbn);
            insert.setInt(2, noBiblioteca);
            insert.setString(3, nombre);
            insert.setString(4, autor);
            insert.setString(5, categoria);
            insert.setInt(6, costo);
            insert.setBoolean(7, true);
            insert.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        return false;
    }
    
    public List<Libro> listarLibros() {
        String query = "SELECT * FROM libro WHERE disponible=?";
        List<Libro> libros = new ArrayList<>();

        try { 
            PreparedStatement select = conexion.prepareStatement(query);
            select.setBoolean(1, true);
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                libros.add(new Libro(resultset.getString("isbn"),
                    resultset.getString("no_biblioteca"),
                    resultset.getString("nombre"),
                    resultset.getString("autor"),
                    resultset.getString("categoria"),
                    resultset.getString("costo"),
                    resultset.getBoolean("disponible"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return libros;
    }

    public List<Libro> listarLibrosEspecificos(int noBiblioteca) {
        String query = "SELECT * FROM libro WHERE no_biblioteca=?";
        List<Libro> libros = new ArrayList<>();

        try {
            PreparedStatement select = conexion.prepareStatement(query);
            select.setInt(1, noBiblioteca);
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                libros.add(new Libro(resultset.getString("isbn"),
                    resultset.getString("no_biblioteca"),
                    resultset.getString("nombre"),
                    resultset.getString("autor"),
                    resultset.getString("categoria"),
                    resultset.getString("costo"),
                    resultset.getBoolean("disponible"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return libros;
    }
    
    public List<Libro> obtenerLibrosEspecificos(String columna, String registro, int noBiblioteca) {
        String query = "SELECT * FROM libro WHERE " + columna + "=? AND no_biblioteca=?";
        List<Libro> libros = new ArrayList<>();

        try {
            PreparedStatement select = conexion.prepareStatement(query);
            select.setString(1, registro);
            select.setInt(2, noBiblioteca);
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                libros.add(new Libro(resultset.getString("isbn"),
                    resultset.getString("no_biblioteca"),
                    resultset.getString("nombre"),
                    resultset.getString("autor"),
                    resultset.getString("categoria"),
                    resultset.getString("costo"),
                    resultset.getBoolean("disponible"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return libros;
    }
    
    public void actualizarLibro(int isbn, String nombre, String autor, String categoria, int costo) {
        String query = "UPDATE libro SET nombre=?, autor=?, categoria=?, costo=? WHERE isbn=?";

        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setString(1, nombre);
            update.setString(2, autor);
            update.setString(3, categoria);
            update.setInt(4, costo);
            update.setInt(5, isbn);
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);
        }
    }
    
    public void eliminarLibro(String isbn) {
        String query = "DELETE FROM libro WHERE isbn = ?";

        try {
            PreparedStatement eliminar = conexion.prepareStatement(query);
            eliminar.setString(1, isbn);
            eliminar.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Libro obtenerLibroPrestamo(String isbn) {
        String query = "SELECT * FROM libro WHERE isbn = ?";
        Libro libro = null;

        try {
            PreparedStatement select = conexion.prepareStatement(query);
            select.setString(1, isbn);
            ResultSet resultset = select.executeQuery();
            
            if (resultset.next()) {
                libro = (new Libro(resultset.getString("isbn"),
                    resultset.getString("no_biblioteca"),
                    resultset.getString("nombre"),
                    resultset.getString("autor"),
                    resultset.getString("categoria"),
                    resultset.getString("costo"),
                    resultset.getBoolean("disponible"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return libro;
    }
}
