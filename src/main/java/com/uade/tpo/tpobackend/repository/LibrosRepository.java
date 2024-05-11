package com.uade.tpo.tpobackend.repository;

import java.util.ArrayList;
import java.util.Arrays;

import com.uade.tpo.tpobackend.entity.Libro;

public class LibrosRepository {
    private ArrayList<Libro> libros = new ArrayList<Libro>(
            Arrays.asList(Libro.builder().nombre("El pistolero").id(1).build(),
                    Libro.builder().nombre("la torre oscura").id(2).build(),
                    Libro.builder().nombre("Harry potter").id(3).build()));

    public ArrayList<Libro> getLibros() {
        return this.libros;
    }

    public Libro getLibrosById(int librosId) {
        return null;
    }

    public String createLibro(String entity) {

        return null;
    }

}
