
package com.mycompany.bibliotecaweb.tipos;

public class Parametro {
    
    private String tipoUsuario;
    private int dias;
    private int limLibros;
    private int suscripcionCosto;
    private int descuento;
    private int multaPorRetraso;
    private int diasSuspendido;
    
    public Parametro(String tipoUsuario, String dias, String limLibros, String suscripcionCosto, String descuento, int multaPorRetraso, int diasSuspendido) {
        this.tipoUsuario = tipoUsuario;
        this.dias = Integer.parseInt(dias);
        this.limLibros = Integer.parseInt(limLibros);
        this.suscripcionCosto = Integer.parseInt(suscripcionCosto);
        this.descuento = Integer.parseInt(descuento);
        this.multaPorRetraso = multaPorRetraso;
        this.diasSuspendido = diasSuspendido;
    }

    public int getMultaPorRetraso() {
        return multaPorRetraso;
    }

    public int getDiasSuspendido() {
        return diasSuspendido;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getLimLibros() {
        return limLibros;
    }

    public void setLimLibros(int limLibros) {
        this.limLibros = limLibros;
    }

    public int getSuscripcionCosto() {
        return suscripcionCosto;
    }

    public void setSuscripcionCosto(int suscripcionCosto) {
        this.suscripcionCosto = suscripcionCosto;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
}