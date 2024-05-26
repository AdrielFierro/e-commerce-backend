package com.uade.tpo.tpobackend.entity;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Usuario {
    public Usuario(){
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuario_id;
    @Column
    private String nombre;
    @Column
    private String pass;
    @Column
    private Boolean admin;
    @Column
    private Double saldo;
    @OneToMany
    @JoinColumn(name = "libro_id")
    private ArrayList<Libro> librosPublicados;
    @OneToMany
    @JoinColumn(name = "venta_id")
    private ArrayList<Venta> ventas;

}
