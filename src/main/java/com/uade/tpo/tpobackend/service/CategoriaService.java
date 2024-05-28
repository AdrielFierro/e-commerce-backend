// CategoriaService.java
package com.uade.tpo.tpobackend.service;

import java.util.List;

import com.uade.tpo.tpobackend.entity.Categoria;

public interface CategoriaService {
    List<Categoria> getCategorias();
    Categoria findById(int id);
    Categoria crearCategoria(Categoria categoria);
   
}
