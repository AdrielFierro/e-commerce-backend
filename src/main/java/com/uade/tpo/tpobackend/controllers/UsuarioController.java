// UsuarioController.java
package com.uade.tpo.tpobackend.controllers;

import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.entity.Venta;
import com.uade.tpo.tpobackend.service.UsuarioService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable int id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public Optional<Usuario> obtenerUsuarioPorNombre(@RequestParam String nombre) {
        return usuarioService.findByNombre(nombre);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        System.out.println(usuario);
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/{id}/ventas")
    public List<Venta> obtenerVentas(@PathVariable int id) {
        return usuarioService.obtenerVentas(id);
    }

    @GetMapping("/{id}/libros")
    public List<Libro> obtenerLibrosPublicados(@PathVariable int id) {
        return usuarioService.obtenerLibrosPublicados(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioActualizado) {
        Usuario usuario = usuarioService.actualizarUsuario(id, usuarioActualizado);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> actualizarParcialmenteUsuario(@PathVariable int id, @RequestBody Usuario usuarioActualizado) {
        Usuario usuario = usuarioService.actualizarParcialmenteUsuario(id, usuarioActualizado);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
