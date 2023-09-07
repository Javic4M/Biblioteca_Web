
package com.mycompany.bibliotecaweb.tipos;

public class SolicitudTransporte {
    
    private int no_transporte;
    private int no_transportista;
    private int no_usuario;
    private int isbn;
    private int dias;
    private String fecha_emision;
    private String estado;
    private String direccion;

    public SolicitudTransporte(int no_transporte, int no_transportista, int no_usuario, int isbn, int dias, String fecha_emision, String estado, String direccion) {
        this.no_transporte = no_transporte;
        this.no_transportista = no_transportista;
        this.no_usuario = no_usuario;
        this.isbn = isbn;
        this.dias = dias;
        this.fecha_emision = fecha_emision;
        this.estado = estado;
        this.direccion = direccion;
    }

    public int getNo_transporte() {
        return no_transporte;
    }

    public int getNo_transportista() {
        return no_transportista;
    }

    public int getNo_usuario() {
        return no_usuario;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getDias() {
        return dias;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public String getEstado() {
        return estado;
    }
    
    public String getDireccion() {
        return direccion;
    }
}
