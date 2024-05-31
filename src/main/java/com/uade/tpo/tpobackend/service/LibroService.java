package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Libro;
import java.util.List;

public interface LibroService {
    List<Libro> getLibros();

    Libro getLibroById(int libroId);

    Libro createLibro(Libro libro);

    void deleteLibroById(int libroId);

    Libro actualizarLibro(int libro_id, Libro libroActualizado);
}
