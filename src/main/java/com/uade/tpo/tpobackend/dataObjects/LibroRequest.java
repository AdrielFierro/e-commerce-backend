package com.uade.tpo.tpobackend.dataObjects;

import java.util.List;

import com.uade.tpo.tpobackend.entity.Categoria;

import lombok.Data;

@Data
public class LibroRequest {
    private String nombre;
    private String descripcion;
    private Double precio;
    private String autor;
    private List<Categoria> cate;
    private int id_usuario;
}
