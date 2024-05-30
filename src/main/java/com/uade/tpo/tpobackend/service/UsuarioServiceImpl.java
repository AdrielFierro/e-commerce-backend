package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.entity.Venta;
import com.uade.tpo.tpobackend.repository.UsuarioRepository;
import com.uade.tpo.tpobackend.util.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Usuario> findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Venta> obtenerVentas(int id) {
        Usuario usuario = findById(id);
        return usuario != null ? usuario.getVentas() : null;
    }

    @Override
    public List<Libro> obtenerLibrosPublicados(int id) {
        Usuario usuario = findById(id);
        return usuario != null ? usuario.getLibrosPublicados() : null;
    }

    @Override
    public Usuario actualizarUsuario(int id, Usuario usuarioActualizado) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setPass(usuarioActualizado.getPass());
            usuario.setAdmin(usuarioActualizado.getAdmin());
            usuario.setSaldo(usuarioActualizado.getSaldo());
            usuario.setLibrosPublicados(usuarioActualizado.getLibrosPublicados());
            usuario.setVentas(usuarioActualizado.getVentas());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    @Override
    public Usuario actualizarParcialmenteUsuario(int id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id).map(usuarioExistente -> {
            Utils.copyNonNullProperties(usuarioActualizado, usuarioExistente);
            return usuarioRepository.save(usuarioExistente);
        }).orElse(null);
    }


}
