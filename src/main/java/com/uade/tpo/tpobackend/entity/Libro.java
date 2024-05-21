package com.uade.tpo.tpobackend.entity;

import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int libro_id;

    @Column
    private String nombre;

    @Column
    private String desc;

    @Column
    private Double precio;

    @Column
    private String autor;

    @OneToMany
    @JoinColumn(name = "categoria_id") 
    private ArrayList<Categoria> cate;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario duenio;

}
