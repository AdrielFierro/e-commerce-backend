package com.uade.tpo.tpobackend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.service.INT.LibrosServiceInterface;
import com.uade.tpo.tpobackend.service.implementacion.LibrosServices;

import java.net.URI;
import java.util.ArrayList;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("libros")

public class LibrosController {

    @Autowired
    private LibrosServiceInterface librosServiceInterface;

    @GetMapping
    public ResponseEntity<ArrayList<Libro>> getLibros() {

        LibrosServiceInterface librosServices = new LibrosServices();
        return ResponseEntity.ok(librosServices.getLibros());
    }

    @GetMapping("/{librosId}")
    public ResponseEntity<Libro> getLibrosById(@PathVariable int librosId) {
       
        LibrosServiceInterface librosServices = new LibrosServices();
        return ResponseEntity.ok(librosServices.getLibrosById(librosId) );
    }

    @PostMapping
    public ResponseEntity< Libro> createLibro(@RequestBody Libro libro) {

        LibrosServiceInterface librosServices = new LibrosServices();
                Libro result = librosServices.createLibro(libro);
        return ResponseEntity.created(URI.create("/libros/" + result.getId())).body(result );
    }

}
