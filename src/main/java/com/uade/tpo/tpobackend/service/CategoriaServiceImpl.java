// CategoriaServiceImpl.java
package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Categoria;
import com.uade.tpo.tpobackend.exceptions.CategoryDuplicateException;
import com.uade.tpo.tpobackend.repository.CategoriaRepository;
import com.uade.tpo.tpobackend.util.Utils;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    public Categoria findById(int categoria_id) {
        return categoriaRepository.findById(categoria_id).orElse(null);
    }

    @Override
    public Optional<Categoria> getCategoriaPorNombre(String nombre) {
        return categoriaRepository.findByNameIgnoreCase(nombre);
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) throws CategoryDuplicateException {
        List<Categoria> categories = categoriaRepository.findByDescripcion(categoria.getDescripcion());
        if (categories.isEmpty()) {
            return categoriaRepository.save(categoria);
        } // inserta o actualiza una fila en la base de datos
        throw new CategoryDuplicateException();
    }

    @Override
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria actualizarCategoria(int id, Categoria categoriaActualizada) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            categoria.setNombre(categoriaActualizada.getNombre());
            categoria.setDescripcion(categoriaActualizada.getDescripcion());
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    @Override
    public Categoria actualizarParcialmenteCategoria(int id, Categoria categoriaActualizada) {
        return categoriaRepository.findById(id).map(categoriaExistente -> {
            Utils.copyNonNullProperties(categoriaActualizada, categoriaExistente);
            return categoriaRepository.save(categoriaExistente);
        }).orElse(null);
    }

}
