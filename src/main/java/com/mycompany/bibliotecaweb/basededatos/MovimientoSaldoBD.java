
package com.mycompany.bibliotecaweb.basededatos;

import com.mycompany.bibliotecaweb.tipos.MovimientoSaldo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimientoSaldoBD {
    
    private Connection conexion;

    public MovimientoSaldoBD(Connection conexion) {
        this.conexion = conexion;
    }
    
    public boolean ingresarMovimientoSaldo(int noUsuario, int saldoAnterior, int cantidadMovilizada, String movimiento, int saldoNuevo) {
        String query = "INSERT INTO movimiento_saldo (no_usuario, saldo_anterior, cantidad_movilizada, movimiento, saldo_nuevo) VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement insert = conexion.prepareStatement(query);
            insert.setInt(1, noUsuario);
            insert.setInt(2, saldoAnterior);
            insert.setInt(3, cantidadMovilizada);
            insert.setString(4, movimiento);
            insert.setInt(5, saldoNuevo);
            insert.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        return false;
    }
    
    public List<MovimientoSaldo> listarMovimientoIngresoYDebito(int noUsuario, String movimiento) {
        String query = "SELECT * FROM movimiento_saldo WHERE no_usuario=? AND movimiento=?";
        List<MovimientoSaldo> movimientosSaldo = new ArrayList<>();

        try { 
            PreparedStatement select = conexion.prepareStatement(query);
            select.setInt(1, noUsuario);
            select.setString(2, movimiento);
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                movimientosSaldo.add(new MovimientoSaldo(
                    resultset.getInt("no_usuario"),
                    resultset.getInt("saldo_anterior"),
                    resultset.getInt("cantidad_movilizada"),
                    resultset.getString("movimiento"),
                    resultset.getInt("saldo_nuevo"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return movimientosSaldo;
    }
}
