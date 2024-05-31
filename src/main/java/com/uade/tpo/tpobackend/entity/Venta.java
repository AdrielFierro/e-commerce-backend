package com.uade.tpo.tpobackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Venta {
    public Venta() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int venta_id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario comprador;

    @OneToMany 
    @JoinColumn(name = "venta_id")
    private List<Libro> libros;

}
