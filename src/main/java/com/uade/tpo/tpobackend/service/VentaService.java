package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Venta;

import java.util.List;

public interface VentaService {
    Venta findById(int id);
    List<Venta> findAll();
    Venta crearVenta(Venta venta);
    double calcularPrecioTotal(int id);
}
