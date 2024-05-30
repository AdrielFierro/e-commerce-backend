package com.uade.tpo.tpobackend.controllers;

import com.uade.tpo.tpobackend.dataObjects.LibroRequest;
import com.uade.tpo.tpobackend.entity.Categoria;
import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.service.LibroService;
import com.uade.tpo.tpobackend.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;




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
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable int id) {
        Libro libro = libroService.getLibroById(id);
        if (libro != null) {
            return ResponseEntity.ok(libro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
  
    @GetMapping("/categorias/{categoria}")
    public List<Libro> getLibrosPorCategoria(@PathVariable String categoria) {
        List<Libro> conjunto = libroService.getLibros();

        List<Libro> filtro = conjunto.stream()
                                    .filter(libro -> libro.getCate()
                                                        .stream()
                                                        .anyMatch(cat -> cat.getNombre()
                                                                            .equalsIgnoreCase(categoria)))
                                    .collect(Collectors.toList());

        return filtro;
    }

    @GetMapping("/autor/{autor}")
    public List<Libro> getLibrosPorAutor(@PathVariable String autor) {
        List<Libro> conjunto = libroService.getLibros();
    
        List<Libro> filtro = conjunto.stream()
                                    .filter(libro -> libro.getAutor().trim().equalsIgnoreCase(autor.trim()))
                                    .collect(Collectors.toList());
    
        return filtro;
    }
    
    
    

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {

    
        return libroService.createLibro(libro);
    }


    @DeleteMapping("/eliminar/")
    public void deleteLibroById(@RequestBody Map<String, Integer> requestBody) {
        int id = requestBody.get("id");
        libroService.deleteLibroById(id);
    }


}
