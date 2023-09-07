
package com.mycompany.bibliotecaweb.basededatos;

import com.mycompany.bibliotecaweb.tipos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioBD {
    
    private Connection conexion;
    
    public UsuarioBD(Connection conexion) {
        this.conexion = conexion;
    }
    
    public boolean crearUsuario(String nombre, String username, String password, String email, int biblioteca) {
        String query = "INSERT INTO usuarios (tipo, no_biblioteca, nombre, username, password, email, suspendido, premiun, saldo) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement insert = conexion.prepareStatement(query);
            insert.setInt(1, 4);
            insert.setInt(2, biblioteca);
            insert.setString(3, nombre);
            insert.setString(4, username);
            insert.setString(5, password);
            insert.setString(6, email);
            insert.setBoolean(7, false);
            insert.setBoolean(8, false);
            insert.setInt(9, 0);
            insert.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        return false;
    }
    
    public boolean crearUsuarioAdmin(int tipo, int noBiblioteca, String nombre, String username, String password, String email, int biblioteca) {
        String query = "INSERT INTO usuarios (tipo, no_biblioteca, nombre, username, password, email, suspendido, premiun, saldo) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement insert = conexion.prepareStatement(query);
            insert.setInt(1, tipo);
            insert.setInt(2, noBiblioteca);
            insert.setString(3, nombre);
            insert.setString(4, username);
            insert.setString(5, password);
            insert.setString(6, email);
            insert.setBoolean(7, false);
            insert.setBoolean(8, false);
            insert.setInt(9, 0);
            insert.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        return false;
    }
    
    public void actualizarUsuario(int codigo, String nombre, String username, String password, String email) {
        String query = "UPDATE usuarios SET nombre=?, username=?, password=?, email=? WHERE codigo=?";

        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setString(1, nombre);
            update.setString(2, username);
            update.setString(3, password);
            update.setString(4, email);
            update.setInt(5, codigo);
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);
        }
    }
    
    public void serPremiun(int codigo) {
        String query = "UPDATE usuarios SET premiun = ? WHERE codigo = ?";

        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setBoolean(1, true);
            update.setInt(2, codigo);
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);
        }
    }
    
    public void recargarSaldo(int codigo, int saldoAnterior, int recarga) {
        String query = "UPDATE usuarios SET saldo = ? WHERE codigo = ?";

        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setInt(1, saldoAnterior + recarga);
            update.setInt(2, codigo);
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);
        }
    }

    public boolean verificarUsername(String username,  int codigo, boolean editar) {
        Usuario usuario = null;
        String query = "SELECT * FROM usuarios WHERE username=?";

        try {
            PreparedStatement select = conexion.prepareStatement(query);
            select.setString(1, username);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                usuario = (new Usuario(resultset.getInt("codigo"),
                        resultset.getInt("tipo"),
                        resultset.getInt("no_biblioteca"),
                        resultset.getString("nombre"),
                        resultset.getString("username"),
                        resultset.getString("password"),
                        resultset.getString("email"),
                        resultset.getBoolean("suspendido"),
                        resultset.getBoolean("premiun"),
                        resultset.getInt("saldo"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (usuario == null) {
            System.out.println("Vacio");
            return true;
        } else {
            if (editar) {
                if (usuario.getCodigo() == codigo) {
                    System.out.println("Igual");
                    return true;
                } else {
                    System.out.println("No igual");
                    return false;
                }
            } else {
                return false;
            }
        }
    }
    
    public List<Usuario> listaUsuariosPorPuesto(int tipo) {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuarios WHERE tipo=?";
  
        try {
            PreparedStatement select = conexion.prepareStatement(query);
            select.setInt(1, tipo);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                usuarios.add(new Usuario(resultset.getInt("codigo"),
                    resultset.getInt("tipo"),
                    resultset.getInt("no_biblioteca"),
                    resultset.getString("nombre"),
                    resultset.getString("username"),
                    resultset.getString("password"),
                    resultset.getString("email"),
                    resultset.getBoolean("suspendido"),
                    resultset.getBoolean("premiun"),
                    resultset.getInt("saldo"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }
    
    public List<Usuario> listaUsuariosPorBiblioteca(int tipo, int noBiblioteca) {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuarios WHERE tipo=? AND no_biblioteca=?";
  
        try {
            PreparedStatement select = conexion.prepareStatement(query);
            select.setInt(1, tipo);
            select.setInt(2, noBiblioteca);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                usuarios.add(new Usuario(resultset.getInt("codigo"),
                    resultset.getInt("tipo"),
                    resultset.getInt("no_biblioteca"),
                    resultset.getString("nombre"),
                    resultset.getString("username"),
                    resultset.getString("password"),
                    resultset.getString("email"),
                    resultset.getBoolean("suspendido"),
                    resultset.getBoolean("premiun"),
                    resultset.getInt("saldo"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }
     
    public List<Usuario> listarUsuariosSuspendidos() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuarios WHERE suspendido=true and tipo=4";
  
        try {
            PreparedStatement select = conexion.prepareStatement(query);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                usuarios.add(new Usuario(resultset.getInt("codigo"),
                    resultset.getInt("tipo"),
                    resultset.getInt("no_biblioteca"),
                    resultset.getString("nombre"),
                    resultset.getString("username"),
                    resultset.getString("password"),
                    resultset.getString("email"),
                    resultset.getBoolean("suspendido"),
                    resultset.getBoolean("premiun"),
                    resultset.getInt("saldo"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }
    
    public Usuario obtenerUsuario(String usernameOCodigo, String password, String tipoDeBusqueda) {
        String query = null;
        Usuario usuario = null;
        
        if ("validar".equals(tipoDeBusqueda)) {
            query = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
        } else if ("prestamo".equals(tipoDeBusqueda)) {
            query = "SELECT * FROM usuarios WHERE codigo = ?";
        }
        
        try {
            PreparedStatement select = conexion.prepareStatement(query);
            if ("validar".equals(tipoDeBusqueda)) {
                select.setString(1, usernameOCodigo);
                select.setString(2, password);
            } else if ("prestamo".equals(tipoDeBusqueda)) {
                select.setString(1, usernameOCodigo);
            }
            ResultSet resultset = select.executeQuery();
            
            if (resultset.next()) {
                usuario = new Usuario(resultset.getInt("codigo"),
                    resultset.getInt("tipo"),
                    resultset.getInt("no_biblioteca"),
                    resultset.getString("nombre"),
                    resultset.getString("username"),
                    resultset.getString("password"),
                    resultset.getString("email"),
                    resultset.getBoolean("suspendido"),
                    resultset.getBoolean("premiun"),
                    resultset.getInt("saldo"));
                System.out.println("No:biblioteca: " + usuario.getBiblioteca());
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return usuario;
    }
    
    public int obtenerTransportista() {
        List<Usuario> usuarios = listaUsuariosPorPuesto(3);
        int numeroDeColumna = (usuarios.size() + 1);
        int transportista = usuarios.get(numeroDeColumna).getCodigo();      
        return transportista;
    }
    
    public void suspenderORevocar(int codigo, boolean estado) {
        String query = "UPDATE usuarios SET suspendido=? WHERE codigo=?";
        
        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setBoolean(1, estado);
            update.setInt(2, codigo);
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);
        }
    }
}