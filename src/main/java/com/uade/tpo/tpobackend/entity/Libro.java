package com.uade.tpo.tpobackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
// import lombok.NoArgsConstructor;

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

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "libro_categoria", joinColumns = @JoinColumn(name = "libro_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> cate;

    @Column(name = "usuarioId")
    private int usuarioId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarioId", insertable = false, updatable = false)
    @JsonIgnore
    private Usuario duenio;

    @Column
    private int stock;

    @Lob
    @Column(name = "BLOB_IMAGE")
    private byte[] imagen;

    // @ManyToMany(cascade = CascadeType.MERGE)
    // @JoinTable(name = "venta_libro", joinColumns = @JoinColumn(name =
    // "venta_id"), inverseJoinColumns = @JoinColumn(name = "libro_id"))
    // private List<Venta> ventas;

}
