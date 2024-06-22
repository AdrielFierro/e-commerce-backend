// CategoriaService.java
package com.uade.tpo.tpobackend.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.tpobackend.entity.Categoria;
import com.uade.tpo.tpobackend.exceptions.CategoryDuplicateException;

public interface CategoriaService {
    List<Categoria> getCategorias();

    Categoria findById(int categoria_id);
    
    Optional<Categoria> getCategoriaPorNombre(String nombre);

    Categoria crearCategoria(Categoria categoria) throws CategoryDuplicateException;

    Categoria actualizarCategoria(int categoria_id, Categoria categoriaActualizada);

    Categoria actualizarParcialmenteCategoria(int categoria_id, Categoria categoriaActualizada);
}
