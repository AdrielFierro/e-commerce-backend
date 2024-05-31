package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.entity.Usuario;
// import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository LibroRepository;

    @Override
    public Libro actualizarLibro(int libro_id, Libro LibroActualizado) {
        Optional<Libro> LibroOptional = LibroRepository.findById(libro_id);
        if (LibroOptional.isPresent()) {
            Libro libro = LibroOptional.get();
            libro.setNombre(LibroActualizado.getNombre());
            libro.setDescripcion(LibroActualizado.getDescripcion());
            libro.setPrecio(LibroActualizado.getPrecio());
            libro.setAutor(LibroActualizado.getAutor());
            libro.setCate(LibroActualizado.getCate());

            return LibroRepository.save(libro);
        }
        return null;
    }

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
    public void deleteLibroById(int libroId) {

        LibroRepository.deleteById(libroId);
        ;
    }

    @Override
    public Usuario getDuenio(int libro_id) {

        Libro libro = getLibroById(libro_id);

        return libro.getDuenio();
    }

}
