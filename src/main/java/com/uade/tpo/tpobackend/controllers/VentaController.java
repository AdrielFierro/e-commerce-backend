package com.uade.tpo.tpobackend.controllers;

import com.uade.tpo.tpobackend.entity.Venta;
import com.uade.tpo.tpobackend.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

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
    public Venta crearVenta(@RequestBody Venta venta) {
        return ventaService.crearVenta(venta);
    }
}
