package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Venta;
import com.uade.tpo.tpobackend.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public Venta findById(int id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta crearVenta(Venta venta) {
        return ventaRepository.save(venta);
    }
}
