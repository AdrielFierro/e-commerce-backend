package com.uade.tpo.tpobackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Categoria {

    public Categoria() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoria_id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @ManyToMany(mappedBy = "cate")
    private List<Libro> libros;
}
