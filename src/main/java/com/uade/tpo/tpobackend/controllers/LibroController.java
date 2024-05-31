package com.uade.tpo.tpobackend.controllers;

import com.uade.tpo.tpobackend.controllers.config.JwtService;
import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.exceptions.LibroInexistenteException;
import com.uade.tpo.tpobackend.service.LibroService;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;
    @Autowired
    private JwtService jwts;

    @GetMapping
    public List<Libro> obtenerLibros() {
        return libroService.getLibros();
    }

    @GetMapping("/test")
    public String test(@RequestHeader("Authorization") String authorizationHeader) {
        authorizationHeader = authorizationHeader.substring(7);

        return jwts.extractId(authorizationHeader);
        // return authorizationHeader;
    }

    @PutMapping("/{libro_id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable int libro_id,
            @RequestBody Libro libroActualizado) {
        Libro libro = libroService.actualizarLibro(libro_id, libroActualizado);
        if (libro != null) {
            return ResponseEntity.ok(libro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable int id) throws LibroInexistenteException {
        Libro libro = libroService.getLibroById(id);
        if (libro != null) {
            return ResponseEntity.ok(libro);
        } else {
            throw new LibroInexistenteException();
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
    public ResponseEntity<Object> crearLibro(@RequestBody Libro libro) throws LibroInexistenteException {
        Libro nuevoLibro = libroService.createLibro(libro);
        if (nuevoLibro == null) {
            throw new LibroInexistenteException();
        }
        return ResponseEntity.created(URI.create("/libros/" + nuevoLibro.getLibro_id())).body(nuevoLibro);
    }

    @DeleteMapping("/eliminar/")
    public void deleteLibroById(@RequestBody Map<String, Integer> requestBody) {
        int id = requestBody.get("id");
        libroService.deleteLibroById(id);
    }

    @GetMapping("/duenio/{libroid}")
    public String getMethodName(@PathVariable int libroid) {
        Usuario usuarioTraido = libroService.getDuenio(libroid);
        String duenio = usuarioTraido.getNombre();

        return duenio;
    }

}