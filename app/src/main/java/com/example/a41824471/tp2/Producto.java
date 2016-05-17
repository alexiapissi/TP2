package com.example.a41824471.tp2;

import java.io.Serializable;

/**
 * Created by Alexia on 10/5/2016.
 */
public class Producto implements Serializable{
    private String categoria;
    private String descripcion;
    private int precio;
    private String foto;

    public Producto(String categoria, String descripcion, int precio, String foto) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String  categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}









