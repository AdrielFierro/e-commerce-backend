// CategoriaController.java
package com.uade.tpo.tpobackend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.uade.tpo.tpobackend.entity.Categoria;
import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.service.CategoriaService;
import com.uade.tpo.tpobackend.exceptions.*;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Optional;


@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> obtenerCategorias() {
        return categoriaService.getCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable int id) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build(); // recurso solicitado no se encontró en el servidor, construye una
                                                      // respuesta HTTP con un cuerpo vacío y un código de estado 404
        }
    }

    @PostMapping
    public ResponseEntity<Object> crearCategoria(@RequestBody Categoria categoria)
            throws CategoryDuplicateException {
        Categoria cat = new Categoria();
        cat.setDescripcion(categoria.getDescripcion());
        cat.setNombre(categoria.getNombre());

       
        Categoria result = categoriaService.crearCategoria(cat);
        return ResponseEntity.created(URI.create("/categoria/" + result.getCategoria_id())).body(result);
        // categoriaService.crearCategoria(categoria)
    }

    @PutMapping("/{categoria_id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable int categoria_id,
            @RequestBody Categoria categoriaActualizada) {
        Categoria categoria = categoriaService.actualizarCategoria(categoria_id, categoriaActualizada);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> actualizarParcialmenteCategoria(@PathVariable int categoria_id,
            @RequestBody Categoria categoriaActualizada) {
        Categoria categoria = categoriaService.actualizarParcialmenteCategoria(categoria_id, categoriaActualizada);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

     @GetMapping("/categorias/{categoria}")
    public List<Libro> getLibrosPorCategoria(@PathVariable String categoria) {
    // Obtener la categoría por nombre utilizando el servicio
    Optional<Categoria> optionalCategoria = categoriaService.getCategoriaPorNombre(categoria);

    // Si la categoría no existe, devolver una lista vacía o manejar el caso según sea necesario
    if (!optionalCategoria.isPresent()) {
        return new ArrayList<>();
    }

    // Obtener los libros de la categoría
    Categoria cat = optionalCategoria.get();
    List<Libro> filtro = cat.getLibros();

    return filtro;
    }
}