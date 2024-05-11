package com.uade.tpo.tpobackend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Libro {

    private int id;
    private String nombre;

}
