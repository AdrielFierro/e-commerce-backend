package com.uade.tpo.tpobackend.controllers;

// import com.uade.tpo.tpobackend.dataObjects.LibroRequest;
import com.uade.tpo.tpobackend.entity.Categoria;
import com.uade.tpo.tpobackend.entity.Libro;
// import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.service.LibroService;
// import com.uade.tpo.tpobackend.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable int id) {
        Libro libro = libroService.getLibroById(id);
        if (libro != null) {
            return ResponseEntity.ok(libro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categorias/{categoria}")
    public List<Libro> getMethodName(@PathVariable String categoria) {
        List<Libro> filtro = new ArrayList<>();

        List<Libro> conjunto = libroService.getLibros();

        for (Libro libro : conjunto) {
            List<Categoria> categoriasLibro = libro.getCate();
            for (Categoria cat : categoriasLibro) {
                if (cat.getNombre().equalsIgnoreCase(categoria)) {
                    System.out.println(true);
                    filtro.add(libro);
                    break;
                }
            }
        }

        return filtro;
    }

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {

        return libroService.createLibro(libro);
    }

}
