
package com.mycompany.bibliotecaweb.basededatos;

import com.mycompany.bibliotecaweb.tipos.Parametro;
import com.mycompany.bibliotecaweb.tipos.Prestamo;
import com.mycompany.bibliotecaweb.tipos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParametrosBD {   
    
    private Connection conexion;
    
    public ParametrosBD(Connection conexion) {
        this.conexion = conexion;
    }
    
    public List<Parametro> obtenerTodosLosParametros() {
        String query = "SELECT * FROM parametros";
        List<Parametro> parametros = new ArrayList<>();
        
        try {
            PreparedStatement select = conexion.prepareStatement(query);
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                parametros.add(new Parametro(resultset.getString("tipo_usuario"),
                    resultset.getString("dias"),
                    resultset.getString("libros"),
                    resultset.getString("suscripcion"),
                    resultset.getString("descuento"),
                    resultset.getInt("multa"),
                    resultset.getInt("dias_suspension"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return parametros;
    }
    
    public Parametro obtenerParametros(String tipoUsuario) {
        String query = "SELECT * FROM parametros WHERE tipo_usuario=?";
        Parametro parametros = null;
        
        try {
            PreparedStatement select = conexion.prepareStatement(query);
            select.setString(1, tipoUsuario);
            ResultSet resultset = select.executeQuery();
            
            while (resultset.next()) {
                parametros = (new Parametro(resultset.getString("tipo_usuario"),
                    resultset.getString("dias"),
                    resultset.getString("libros"),
                    resultset.getString("suscripcion"),
                    resultset.getString("descuento"),
                    resultset.getInt("multa"),
                    resultset.getInt("dias_suspension"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return parametros;
    }
    
    public void actualizarParametros(String tipoDeUsurio, int dias, int libros, int precio, int descuento, int multa, int suspension) {
        String query = "UPDATE parametros SET dias=?, libros=?, suscripcion=?, descuento=?, multa=?, dias_suspension=?  WHERE tipo_usuario=?";

        try {
            PreparedStatement update = conexion.prepareStatement(query);
            update.setInt(1, dias);
            update.setInt(2, libros);
            update.setInt(3, precio);
            update.setInt(4, descuento);
            update.setInt(5, multa);
            update.setInt(6, suspension);
            update.setString(7, tipoDeUsurio);
            update.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar tarea: " + e);
        }
    }
    
    public boolean verificarFecha(boolean tipoDeUsuario, String fechaPrestamo) {
        Date todayDate = new Date();

        SimpleDateFormat dia = new SimpleDateFormat("dd");
        SimpleDateFormat mes = new SimpleDateFormat("MM");
        SimpleDateFormat ano = new SimpleDateFormat("yyyy");

        String fDia = dia.format(todayDate);
        String fMes = mes.format(todayDate);
        String faño = ano.format(todayDate);

        String fechaActual = faño + "-" + fMes + "-" + fDia;
        long dias = 0;
        
        try {
            SimpleDateFormat fechaF = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicial = fechaF.parse(fechaActual);
            Date fechaFinal = fechaF.parse(fechaPrestamo);
            
            long tiempoTranscurrido = fechaFinal.getTime() - fechaInicial.getTime();
            TimeUnit unidad = TimeUnit.DAYS;
            
            dias = unidad.convert(tiempoTranscurrido, TimeUnit.MILLISECONDS);
            System.out.println("Dias: " + dias);
        } catch (ParseException ex) {
            System.out.println("Error al consultar: " + ex);
        }

        return verificarDias(tipoDeUsuario, dias);
    }
    
    public boolean verificarDias(boolean tipoDeUsuario, long dias) {        
        String query = "SELECT * FROM parametros WHERE tipo_usuario = ?";
        Parametro parametro = null;
        
        try {
            PreparedStatement select = conexion.prepareStatement(query);
            if (tipoDeUsuario) {
                select.setString(1, "premiun");
            } else {
                select.setString(1, "normal");
            }
            ResultSet resultset = select.executeQuery();
            
            if (resultset.next()) {
                parametro = new Parametro(resultset.getString("tipo_usuario"),
                    resultset.getString("dias"),
                    resultset.getString("libros"),
                    resultset.getString("suscripcion"),
                    resultset.getString("descuento"),
                    resultset.getInt("multa"),
                    resultset.getInt("dias_suspension")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        
        if (dias > 0) {
            if (dias <= parametro.getDias()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public int verirficarCostoDeSuscripcion() {
        String query = "SELECT * FROM parametros WHERE tipo_usuario=?";
        Parametro parametros = null;
        
        try {
            PreparedStatement select = conexion.prepareStatement(query);
            select.setString(1, "premiun");
            ResultSet resultset = select.executeQuery();
            
            if (resultset.next()) {
                parametros = new Parametro(resultset.getString("tipo_usuario"),
                    resultset.getString("dias"),
                    resultset.getString("libros"),
                    resultset.getString("suscripcion"),
                    resultset.getString("descuento"),
                    resultset.getInt("multa"),
                    resultset.getInt("dias_suspension")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return parametros.getSuscripcionCosto();
    }
    
    public void verificarPrestamos(Usuario usuario, Connection conexion) {
        PrestamoBD prestamoBD = new PrestamoBD(conexion);
        List<Prestamo> prestamos = prestamoBD.listarPrestamoSinMulta(usuario.getCodigo());
                
        for (Prestamo prestamo : prestamos) {           
            if (!verificarFecha(usuario.isPremiun(), prestamo.getFechaEntrega())) {
                prestamoBD.marcarMulta(prestamo.getNoPrestamo(), usuario.isPremiun());
            }
        }
    }
}
