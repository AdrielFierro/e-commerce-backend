package com.uade.tpo.tpobackend.entity;



import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Libro {
    public Libro(){
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

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario duenio;

}
