
package com.mycompany.bibliotecaweb.tipos;

public class Incidencia {
    
    private int noUsuario;
    private String descripcion;
    private boolean adminElimino;
    private boolean usuarioPago;
    private int costo;
    private int isbn;

    public Incidencia(int noUsuario, String descripcion, boolean adminElimino, boolean usuarioPago, int costo, int isbn) {
        this.noUsuario = noUsuario;
        this.descripcion = descripcion;
        this.adminElimino = adminElimino;
        this.usuarioPago = usuarioPago;
        this.costo = costo;
        this.isbn = isbn;
    }

    public int getNoUsuario() {
        return noUsuario;
    }
    
    public int getCosto() {
        return costo;
    }

    public boolean isAdminElimino() {
        return adminElimino;
    }

    public boolean isUsuarioPago() {
        return usuarioPago;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
