
package com.mycompany.bibliotecaweb.tipos;

public class Prestamo {
    
    private int noPrestamo;
    private int noBiblioteca;
    private int noRecepcionista;
    private int noUsuario;
    private int ISBN;
    private String fechaEntrega;
    private String estadoPrestamo;
    private String pago;
    private int multa;

    public Prestamo(int noPrestamo, int noBiblioteca, String noRecepcionista, String noUsuario, String ISBN, String fechaEntrega, String estadoPrestamo, String pago, String multa) {
        this.noPrestamo = noPrestamo;
        this.noBiblioteca = noBiblioteca;
        this.noRecepcionista = Integer.parseInt(noRecepcionista);
        this.noUsuario = Integer.parseInt(noUsuario);
        this.ISBN = Integer.parseInt(ISBN);
        this.fechaEntrega = fechaEntrega;
        this.estadoPrestamo = estadoPrestamo;
        this.pago = pago;
        this.multa = Integer.parseInt(multa);
    }

    public int getNoPrestamo() {
        return noPrestamo;
    }

    public void setNoPrestamo(int noPrestamo) {
        this.noPrestamo = noPrestamo;
    }

    public int getNoBiblioteca() {
        return noBiblioteca;
    }

    public void setNoBiblioteca(int noBiblioteca) {
        this.noBiblioteca = noBiblioteca;
    }

    public int getNoRecepcionista() {
        return noRecepcionista;
    }

    public void setNoRecepcionista(int noRecepcionista) {
        this.noRecepcionista = noRecepcionista;
    }

    public int getNoUsuario() {
        return noUsuario;
    }

    public void setNoUsuario(int noUsuario) {
        this.noUsuario = noUsuario;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }
}
