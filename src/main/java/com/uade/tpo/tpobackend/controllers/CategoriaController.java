// CategoriaController.java
package com.uade.tpo.tpobackend.controllers;
import java.util.List;

import com.uade.tpo.tpobackend.entity.Categoria;
import com.uade.tpo.tpobackend.service.CategoriaService;
import com.uade.tpo.tpobackend.exceptions.*;
import com.uade.tpo.tpobackend.dataObjects.*;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


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
    public ResponseEntity<Object> crearCategoria(@RequestBody CategoryRequest categoria) 
        throws CategoryDuplicateException {
            Categoria cat= new Categoria();
            cat.setDescripcion(categoria.getDescripcion());
            cat.setNombre(categoria.getNombre());

            System.out.println(categoria);
            Categoria result= categoriaService.crearCategoria(cat);
        return ResponseEntity.created(URI.create("/categorias/"+result.getId())).body(result);
        // categoriaService.crearCategoria(categoria)
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable int id, @RequestBody Categoria categoriaActualizada) {
        Categoria categoria = categoriaService.actualizarCategoria(id, categoriaActualizada);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> actualizarParcialmenteCategoria(@PathVariable int id, @RequestBody Categoria categoriaActualizada) {
        Categoria categoria = categoriaService.actualizarParcialmenteCategoria(id, categoriaActualizada);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
