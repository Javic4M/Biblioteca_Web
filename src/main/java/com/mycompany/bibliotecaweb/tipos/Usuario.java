
package com.mycompany.bibliotecaweb.tipos;

public class Usuario {
    
    private int codigo;
    private int puesto;
    private int biblioteca;
    private String nombre;
    private String username;
    private String password;
    private String email;
    private boolean suspendido;
    private boolean premiun;
    private int saldo = 0;
    private String jerarquia;
    
    public Usuario(int codigo, int puesto, int biblioteca, String nombre, String username, String password, String email, boolean suspendido, boolean premiun, int saldo) {
        this.codigo = codigo;
        this.puesto = puesto;
        this.biblioteca = biblioteca;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.email = email;
        this.suspendido = suspendido;
        this.premiun = premiun;
        this.saldo = saldo;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getPuesto() {
        return puesto;
    }

    public int getBiblioteca() {
        return biblioteca;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }

    public boolean isSuspendido() {
        return suspendido;
    }

    public void suspenderUsuario() {
        suspendido = true;
    }
    
    public void revocarSuspension() {
        suspendido = false;
    }
    
    public void setPremiun() {
        premiun = true;
    }
    
    public boolean isPremiun() {
        return premiun;
    }

    public void setSaldo(int recarga) {
        saldo += recarga;
    }
    
    public int getSaldo() {
        return saldo;
    } 
    
    public void getJerarquia() {
        if (puesto == 1) {
            jerarquia = "Administrado";
        } else if (puesto == 2) {
            jerarquia = "Recepcionista";
        } else if (puesto == 2) {
            jerarquia = "Usuario";
        }
    }
}
