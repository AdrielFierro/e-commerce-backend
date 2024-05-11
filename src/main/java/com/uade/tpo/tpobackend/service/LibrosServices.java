package com.uade.tpo.tpobackend.service;

import java.util.ArrayList;

import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.repository.LibrosRepository;

public class LibrosServices {

    public ArrayList<Libro> getLibros() {
        LibrosRepository librosRepository = new LibrosRepository();

        return librosRepository.getLibros();
    }

    public Libro getLibrosById(int librosId) {
        LibrosRepository librosRepository = new LibrosRepository();
        return librosRepository.getLibrosById(librosId);
    }

    public String createLibro(String entity) {
        LibrosRepository librosRepository = new LibrosRepository();

        return entity;
    }

}
