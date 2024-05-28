// CategoriaController.java
package com.uade.tpo.tpobackend.controllers;
import java.util.List;

import com.uade.tpo.tpobackend.entity.Categoria;
import com.uade.tpo.tpobackend.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/categorias")
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
            return ResponseEntity.notFound().build(); //recurso solicitado no se encontró en el servidor, construye una respuesta HTTP con un cuerpo vacío y un código de estado 404
        }
    }

    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.crearCategoria(categoria);
    }
}
