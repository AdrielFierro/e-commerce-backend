package com.uade.tpo.tpobackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Venta {
    public Venta() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int venta_id;

    @Column(name = "compradorid")
    private int compradorid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "compradorid", insertable = false, updatable = false)
    @JsonIgnore
    private Usuario comprador;

    @Column
    private double precioTotal;

    @OneToMany(mappedBy = "ventadueña")
    private List<cantlibros> cantlibros;

    public int getCompradorId() {

        return compradorid;
    }

}
