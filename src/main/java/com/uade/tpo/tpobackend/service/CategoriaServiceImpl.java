// CategoriaServiceImpl.java
package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Categoria;
import com.uade.tpo.tpobackend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria findById(int id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria); //inserta o actualiza una fila en la base de datos
    }

}
