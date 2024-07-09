package com.uade.tpo.tpobackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class cantlibros {

    public cantlibros() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cantlibros_id;

    @Column(name = "ventaId")
    private int ventaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ventaId", insertable = false, updatable = false)
    @JsonIgnore
    private Venta ventadueña;

    @Column
    private int cantidad;

    @Column
    private int libroid;

}
