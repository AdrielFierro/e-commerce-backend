package com.uade.tpo.tpobackend.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "imagen")
@Data
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "datos_imagen", columnDefinition = "LONGBLOB")
    private byte[] datosImagen;

}
