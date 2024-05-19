package com.uade.tpo.tpobackend.repository;

import java.util.ArrayList;
import java.util.Arrays;

import com.uade.tpo.tpobackend.entity.Libro;

public class LibrosRepository {
    private Libro libro1= new Libro("descripcion1",20000.0,"Autor1","Libro1");
    private Libro libro2= new Libro("descripcion2",30000.0,"Autor2","Libro2");
    private Libro libro3= new Libro("descripcion3",40000.0,"Autor3","Libro3");
    private Libro libro4= new Libro("descripcion4",30000.0,"Autor4","Libro4");
    private ArrayList<Libro> libros = new ArrayList<Libro>(
            Arrays.asList(
            libro1,
            libro2,
            libro3,
            libro4
            )
            );

    public ArrayList<Libro> getLibros() {
        return this.libros;
    }

    public Libro getLibrosById(int librosId) {

        return null;
    }

    public Libro createLibro(Libro entity) {
        Libro nuevo= entity;
            libros.add(nuevo);

        return nuevo;
    }

}
