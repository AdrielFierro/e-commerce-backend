package com.uade.tpo.tpobackend.controllers;

import com.uade.tpo.tpobackend.controllers.config.JwtService;
import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.exceptions.LibroInexistenteException;
import com.uade.tpo.tpobackend.exceptions.NoMatchUsuarioException;
import com.uade.tpo.tpobackend.service.LibroService;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
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

    @PutMapping("/{libro_id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable int libro_id,
            @RequestBody Libro libroActualizado, @RequestHeader("Authorization") String authorizationHeader)
            throws NoMatchUsuarioException {
        authorizationHeader = authorizationHeader.substring(7);

        int idusuario = jwts.extractId(authorizationHeader);

        Libro libroAactualizar = libroService.getLibroById(libro_id);
        int idUsuarioLibro = libroAactualizar.getUsuarioId();
        if (idusuario == idUsuarioLibro) {
            Libro libro = libroService.actualizarLibro(libro_id, libroActualizado);
            if (libro != null) {
                return ResponseEntity.ok(libro);
            } else {
                return ResponseEntity.notFound().build();
            }

        } else {
            throw new NoMatchUsuarioException();
        }

    }

    @PutMapping("/stock/{libro_id}")
    public ResponseEntity<Integer> actualizarStock(@PathVariable int libro_id,
            @RequestBody int stockAsumar, @RequestHeader("Authorization") String authorizationHeader)
            throws NoMatchUsuarioException {

        authorizationHeader = authorizationHeader.substring(7);
        int idusuario = jwts.extractId(authorizationHeader);

        Libro libroAactualizar = libroService.getLibroById(libro_id);
        int idUsuarioLibro = libroAactualizar.getUsuarioId();

        if (idusuario == idUsuarioLibro) {

            if (libroService.getLibroById(libro_id) != null) {
                int stocklibro = libroService.sumarStockLibro(libro_id, stockAsumar);
                return ResponseEntity.ok(stocklibro);
            } else {
                return ResponseEntity.notFound().build();
            }

        } else {
            throw new NoMatchUsuarioException();
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

    @GetMapping("/mislibros")
    public List<Libro> getMisLibros(@RequestHeader("Authorization") String authorizationHeader) {
        List<Libro> LibrosAll = libroService.getLibros();
        authorizationHeader = authorizationHeader.substring(7);
        int idusuario = jwts.extractId(authorizationHeader);

        List<Libro> librosFiltrados = LibrosAll.stream()
                .filter(libro -> libro.getUsuarioId() == idusuario)
                .collect(Collectors.toList());

        List<Integer> idLibrosFiltrados = new ArrayList<>();

        for (Libro l : librosFiltrados) {

            idLibrosFiltrados.add(l.getLibro_id());

        }

        return librosFiltrados;
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
    public ResponseEntity<Object> crearLibro(@RequestBody Libro libro,
            @RequestHeader("Authorization") String authorizationHeader) throws LibroInexistenteException {
        authorizationHeader = authorizationHeader.substring(7);

        libro.setUsuarioId(jwts.extractId(authorizationHeader));

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