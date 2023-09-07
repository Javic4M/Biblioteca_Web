
package com.mycompany.bibliotecaweb.tipos;

public class Libro {
    
    private int ISNB;
    private int biblioteca;
    private String nombre;
    private String autor;
    private String categoria;
    private int costo;
    private boolean disponible;

    public Libro(String ISNB, String biblioteca, String nombre, String autor, String categoria, String costo, boolean disponible) {
        this.ISNB = Integer.parseInt(ISNB);
        this.biblioteca = Integer.parseInt(biblioteca);
        this.nombre = nombre;
        this.autor = autor;
        this.categoria = categoria;
        this.costo = Integer.parseInt(costo);
        this.disponible = disponible;
    }

    public int getISBN() {
        return ISNB;
    }

    public void ponerISNB(int ISNB) {
        this.ISNB = ISNB;
    }

    public int getBiblioteca() {
        return biblioteca;
    }

    public void ponerBiblioteca(int biblioteca) {
        this.biblioteca = biblioteca;
    }

    public String getNombre() {
        return nombre;
    }

    public void ponerNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void ponerAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void ponerCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCosto() {
        return costo;
    }

    public void ponerCosto(int costo) {
        this.costo = costo;
    }

    public boolean isDisponible() {
        return disponible;
    }    
}
