package com.uade.tpo.tpobackend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.service.LibrosServices;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("libros")

public class LibrosController {

    @GetMapping
    public ArrayList<Libro> getLibros() {

        LibrosServices librosServices = new LibrosServices();
        return librosServices.getLibros();
    }

    @GetMapping("/{librosId}")
    public Libro getLibrosById(@PathVariable int librosId) {
        LibrosServices librosServices = new LibrosServices();
        return librosServices.getLibrosById(librosId);
    }

    @PostMapping
    public String createLibro(@RequestBody String librosId) {

        LibrosServices librosServices = new LibrosServices();
        return librosServices.createLibro(librosId);
    }

}
