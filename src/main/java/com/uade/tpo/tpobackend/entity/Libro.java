package com.uade.tpo.tpobackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String descripcion;
    @Column
    private String nombre;
    @Column
    private String Autor;
    @Column
    private double precio;
    
    public Libro( String descripcion, double precio,String Autor,String nombre) {
        
        this.descripcion = descripcion;
        this.precio = precio;
        this.nombre= nombre;
        this.Autor=Autor;
    }

    public void Modificar(String descripcion, double precio,String Autor,String nombre){
        this.descripcion = descripcion;
        this.precio = precio;
        this.Autor=Autor;
        this.nombre=nombre;
    }
}
