/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecaweb.tipos;

/**
 *
 * @author DELL
 */
public class SolicitudRevocacion {
    
    private int noUsuario;
    private String descripcion;
    private String estado;

    public SolicitudRevocacion(int noUsuario, String descripcion, String estado) {
        this.noUsuario = noUsuario;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getNoUsuario() {
        return noUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }
    
    
}
