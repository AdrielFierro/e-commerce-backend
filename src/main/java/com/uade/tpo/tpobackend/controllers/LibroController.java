package com.uade.tpo.tpobackend.controllers;

import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> obtenerLibros() {
        return libroService.getLibros();
    }

    @GetMapping("/{id}")
    public Libro obtenerLibroPorId(@PathVariable int id) {
        return libroService.getLibroById(id);
    }

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroService.createLibro(libro);
    }
}
