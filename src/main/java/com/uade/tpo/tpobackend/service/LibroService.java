package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Libro;

import com.uade.tpo.tpobackend.entity.Usuario;

import java.util.List;

public interface LibroService {
    List<Libro> getLibros();

    Libro getLibroById(int libroId);

    Libro createLibro(Libro libro);

    void deleteLibroById(int libroId);

    Libro actualizarLibro(int libro_id, Libro libroActualizado);

    void venderLibros(int libroId, int cantARestar);

    Usuario getDuenio(int libro_id);
}
