package com.uade.tpo.tpobackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int venta_id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario comprador;

    @OneToMany 
    @JoinColumn(name = "venta_id")
    private List<Libro> libros;

    
    public Venta() {}

    
    public double calcularPrecioTotal() {
        double precioTotal = 0.0;
        for (Libro libro : libros) {
            precioTotal += libro.getPrecio();
        }
        return precioTotal;
    }
}
