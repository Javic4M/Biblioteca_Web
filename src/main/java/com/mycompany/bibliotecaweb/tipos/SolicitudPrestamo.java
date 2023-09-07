
package com.mycompany.bibliotecaweb.tipos;

public class SolicitudPrestamo {
    
    private int noSolicitudPrestamo;
    private int noBiblioteca;
    private int noUsuario;
    private int isbn;
    private int dias;
    private String fechaEmision;
    private String estadoSolicitud;

    public SolicitudPrestamo(int noSolicitudPrestamo, int noBiblioteca, int noUsuario, int isbn, int dias, String fechaEmision, String estadoSolicitud) {
        this.noSolicitudPrestamo = noSolicitudPrestamo;
        this.noBiblioteca = noBiblioteca;
        this.noUsuario = noUsuario;
        this.isbn = isbn;
        this.dias = dias;
        this.fechaEmision = fechaEmision;
        this.estadoSolicitud = estadoSolicitud;
    }

    public int getNoSolicitudPrestamo() {
        return noSolicitudPrestamo;
    }

    public int getNoBiblioteca() {
        return noBiblioteca;
    }

    public int getNoUsuario() {
        return noUsuario;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getDias() {
        return dias;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }
}
