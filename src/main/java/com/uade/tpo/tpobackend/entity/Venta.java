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

    @Column
    private int comprador_ID;

    @Column
    private int vendedor_ID;

    @OneToMany
    @JoinColumn(name = "venta_id")
    private List<Libro> libros;

}
