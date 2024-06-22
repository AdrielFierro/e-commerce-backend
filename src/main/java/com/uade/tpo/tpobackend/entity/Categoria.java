package com.uade.tpo.tpobackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Data
@Entity
public class Categoria {

    public Categoria() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoria_id;
    @Column
    private String nombre;
    @Column
    private String descripcion;

    @ManyToMany(mappedBy = "categorias")
    private List<Libro> libros;
}
