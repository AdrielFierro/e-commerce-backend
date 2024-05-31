// UsuarioService.java
package com.uade.tpo.tpobackend.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.entity.Venta;

public interface UsuarioService {
    Usuario findById(int id);

    Optional<Usuario> findByNombre(String nombre);

    // Usuario crearUsuario(Usuario usuario);

    List<Venta> obtenerVentas(int id);

    List<Libro> obtenerLibrosPublicados(int id);

    Usuario actualizarUsuario(int id, Usuario usuarioActualizado);

    Usuario actualizarParcialmenteUsuario(int id, Usuario usuarioActualizado);
}
