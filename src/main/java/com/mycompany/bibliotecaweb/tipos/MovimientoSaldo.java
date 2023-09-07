
package com.mycompany.bibliotecaweb.tipos;

public class MovimientoSaldo {
    
    private int no_usuario;
    private int saldoAnterior;
    private int cantidadMovilizada;
    private String movimiento;
    private int saldoNuevo;

    public MovimientoSaldo(int no_usuario, int saldoAnterior, int cantidadMovilizada, String movimiento, int saldoNuevo) {
        this.no_usuario = no_usuario;
        this.saldoAnterior = saldoAnterior;
        this.cantidadMovilizada = cantidadMovilizada;
        this.movimiento = movimiento;
        this.saldoNuevo = saldoNuevo;
    }

    public int getNo_usuario() {
        return no_usuario;
    }

    public int getSaldoAnterior() {
        return saldoAnterior;
    }

    public int getCantidadMovilizada() {
        return cantidadMovilizada;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public int getSaldoNuevo() {
        return saldoNuevo;
    }
}
