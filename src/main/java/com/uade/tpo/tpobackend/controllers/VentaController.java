package com.uade.tpo.tpobackend.controllers;

import com.uade.tpo.tpobackend.controllers.config.JwtService;
import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.entity.Venta;
import com.uade.tpo.tpobackend.entity.cantlibros;
import com.uade.tpo.tpobackend.exceptions.CategoryDuplicateException;
import com.uade.tpo.tpobackend.exceptions.UsuarioNoEncontradoException;
import com.uade.tpo.tpobackend.repository.cantlibrosRepository;
import com.uade.tpo.tpobackend.service.LibroService;
import com.uade.tpo.tpobackend.service.UsuarioService;
import com.uade.tpo.tpobackend.service.VentaService;
import com.uade.tpo.tpobackend.service.cantlibrosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uade.tpo.tpobackend.exceptions.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
    public Venta crearVenta(@RequestBody List<ObjVenta> objventa,
            @RequestHeader("Authorization") String authorizationHeader) throws UsuarioNoEncontradoException {

        authorizationHeader = authorizationHeader.substring(7);

        Venta venta = new Venta();

        venta.setCompradorid(jwts.extractId(authorizationHeader));

        List<cantlibros> cantlibros = new ArrayList<cantlibros>();

        Venta ventavacia = ventaService.crearVenta(venta);

        int idventavacia = ventavacia.getVenta_id();

        for (ObjVenta ov : objventa) {

            cantlibros cantaux = new cantlibros();

            cantaux.setCantidad(ov.cantidad);
            int libroid = ov.idlibro;
            Libro libro = libroService.getLibroById(libroid);
            cantaux.setLibroid(libroid);
            cantaux.setCantidad(ov.cantidad);
            cantaux.setVentaId(idventavacia);
            cantlibrosService.crearCantLibros(cantaux);
            cantlibros.add(cantaux);
        }

        return ventaService.findById(idventavacia);
    }

}
