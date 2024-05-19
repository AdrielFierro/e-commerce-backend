package com.uade.tpo.tpobackend.service.implementacion;

import java.util.ArrayList;


import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.repository.LibrosRepository;
import com.uade.tpo.tpobackend.service.INT.LibrosServiceInterface;

public class LibrosServices implements LibrosServiceInterface{

    public ArrayList<Libro> getLibros() {
        LibrosRepository librosRepository = new LibrosRepository();

        return librosRepository.getLibros();
    }

    public Libro getLibrosById(int librosId) {
        LibrosRepository librosRepository = new LibrosRepository();
        return librosRepository.getLibrosById(librosId);
    }

    public Libro createLibro(Libro entity) {



        return entity;
    }

}
