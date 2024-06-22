package com.uade.tpo.tpobackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "libro_id")
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

    
    @Column(name = "usuarioId")
    private int usuarioId;
    
    @Column
    private int stock;

    @ManyToMany 
    @JoinTable(name = "libro_categoria", joinColumns = @JoinColumn(name = "libro_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    
    private List<Categoria> categorias;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarioId", insertable = false, updatable = false)
    @JsonIgnore
    private Usuario duenio;

}
