package com.uade.tpo.tpobackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Libro {
    public Libro() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int libro_id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private Double precio;

    @Column
    private String autor;

    @OneToMany
    @JoinColumn(name = "categoria_id")
    private List<Categoria> cate;

    @Column(name = "usuarioId")
    private int usuarioId;

    @Column
    private int stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarioId", insertable = false, updatable = false)
    @JsonIgnore
    private Usuario duenio;

}
