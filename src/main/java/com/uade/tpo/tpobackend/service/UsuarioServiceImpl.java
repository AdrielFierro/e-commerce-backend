// UsuarioServiceImpl.java
package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.entity.Venta;
import com.uade.tpo.tpobackend.repository.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Venta> obtenerVentas(int id) {
        return findById(id).getVentas();       
    }

    @Override
    public List<Libro> obtenerLibrosPublicados(int id){
        return findById(id).getLibrosPublicados();
    }
}
