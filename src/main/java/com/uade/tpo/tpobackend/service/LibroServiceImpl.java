package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository LibroRepository;

    @Override
    public List<Libro> getLibros() {
        return LibroRepository.findAll();
    }

    @Override
    public Libro getLibroById(int libroId) {
        return LibroRepository.findById(libroId).orElse(null);
    }

    @Override
    public Libro createLibro(Libro libro) {
        return LibroRepository.save(libro);
    }

    @Override
    public void deleteLibroById(int libroId){
        
         LibroRepository.deleteById(libroId);;
    }
}
