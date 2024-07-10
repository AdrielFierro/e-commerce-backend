package com.uade.tpo.tpobackend.controllers;

import com.uade.tpo.tpobackend.controllers.config.JwtService;
import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.entity.Venta;
import com.uade.tpo.tpobackend.entity.cantlibros;
import com.uade.tpo.tpobackend.entity.respuestaApi;
import com.uade.tpo.tpobackend.exceptions.CategoryDuplicateException;
import com.uade.tpo.tpobackend.exceptions.UsuarioNoEncontradoException;
import com.uade.tpo.tpobackend.repository.cantlibrosRepository;
import com.uade.tpo.tpobackend.service.LibroService;
import com.uade.tpo.tpobackend.service.UsuarioService;
import com.uade.tpo.tpobackend.service.VentaService;
import com.uade.tpo.tpobackend.service.cantlibrosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uade.tpo.tpobackend.exceptions.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;
    @Autowired
    private JwtService jwts;
    @Autowired
    private LibroService libroService;

    @Autowired
    private UsuarioService UsuarioService;

    @Autowired
    private cantlibrosService cantlibrosService;

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable int id) {
        Venta venta = ventaService.findById(id);
        if (venta != null) {
            return ResponseEntity.ok(venta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Venta> obtenerTodasLasVentas() {
        return ventaService.findAll();
    }

    @PostMapping
    public ResponseEntity<Venta> crearVenta(@RequestBody List<ObjVenta> objventa,
            @RequestHeader("Authorization") String authorizationHeader) throws UsuarioNoEncontradoException {

        boolean stockSuf = true;

        for (ObjVenta ov : objventa) {

            int libroid = ov.idlibro;
            Libro libro = libroService.getLibroById(libroid);

            int stockLibro = libro.getStock();
            int cantAcomprarLibro = ov.cantidad;

            if (stockLibro < cantAcomprarLibro) {
                throw new RuntimeException("no hay stock suficiente para el libro " + libro.getNombre());
            }

        }

        if (stockSuf == true) {

            authorizationHeader = authorizationHeader.substring(7);

            Venta venta = new Venta();

            venta.setCompradorid(jwts.extractId(authorizationHeader));

            List<cantlibros> cantlibros = new ArrayList<cantlibros>();

            Venta ventavacia = ventaService.crearVenta(venta);

            int idventavacia = ventavacia.getVenta_id();

            double precioTotal = 0;

            for (ObjVenta ov : objventa) {

                cantlibros cantaux = new cantlibros();

                cantaux.setCantidad(ov.cantidad);
                int libroid = ov.idlibro;
                Libro libro = libroService.getLibroById(libroid);
                libroService.venderLibros(libro.getLibro_id(), ov.cantidad);
                cantaux.setLibroid(libroid);
                cantaux.setCantidad(ov.cantidad);
                cantaux.setVentaId(idventavacia);
                cantlibrosService.crearCantLibros(cantaux);
                cantlibros.add(cantaux);

                precioTotal = precioTotal + (libro.getPrecio() * ov.cantidad);
            }

            ventaService.setPrecioTotal(idventavacia, precioTotal);

            return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.findById(idventavacia));
        }

        else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("/misventas")
    public List<Venta> getMisVentas(@RequestHeader("Authorization") String authorizationHeader) {

        List<Venta> VentaAll = ventaService.findAll();
        authorizationHeader = authorizationHeader.substring(7);
        int idusuario = jwts.extractId(authorizationHeader);

        List<Venta> ventasFiltradas = VentaAll.stream()
                .filter(venta -> venta.getCompradorId() == idusuario)
                .collect(Collectors.toList());

        // List<Integer> idLibrosFiltrados = new ArrayList<>();

        // for (Libro l : librosFiltrados) {

        // idLibrosFiltrados.add(l.getLibro_id());

        // }

        return ventasFiltradas;
    }

}
