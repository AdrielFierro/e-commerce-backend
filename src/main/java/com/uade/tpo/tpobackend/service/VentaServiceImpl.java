package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.entity.Venta;
import com.uade.tpo.tpobackend.repository.LibroRepository;
import com.uade.tpo.tpobackend.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void setPrecioTotal(int ventaid, double precioTotal) {

        Optional<Venta> ventaOptional = ventaRepository.findById(ventaid);
        if (ventaOptional.isPresent()) {
            Venta venta = ventaOptional.get();
            venta.setPrecioTotal(precioTotal);
            ventaRepository.save(venta);
        }

    }

    @Override
    public void deleteVentaById(int ventaId) {

        ventaRepository.deleteById(ventaId);

    }

}
