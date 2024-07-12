// UsuarioController.java
package com.uade.tpo.tpobackend.controllers;

import com.uade.tpo.tpobackend.controllers.config.JwtService;
import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.entity.Role;
import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.entity.Venta;
import com.uade.tpo.tpobackend.service.LibroService;
import com.uade.tpo.tpobackend.service.UsuarioService;
import com.uade.tpo.tpobackend.service.VentaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LibroService libroService;
    @Autowired
    private VentaService ventaService;
    @Autowired
    private JwtService jwts;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable int id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public Optional<Usuario> obtenerUsuarioPorNombre(@RequestParam String nombre) {
        return usuarioService.findByNombre(nombre);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}/ventas")
    public List<Venta> obtenerVentas(@PathVariable int id) {
        return usuarioService.obtenerVentas(id);
    }

    @GetMapping("/{id}/libros")
    public List<Libro> obtenerLibrosPublicados(@PathVariable int id) {
        return usuarioService.obtenerLibrosPublicados(id);
    }

    @GetMapping("/admin")
    public boolean soyAdmin(@RequestHeader("Authorization") String authorizationHeader) {

        authorizationHeader = authorizationHeader.substring(7); // Eliminar "Bearer "
        int idusuario = jwts.extractId(authorizationHeader);

        Usuario usuario = usuarioService.findById(idusuario);
        Role roleadmin = Role.ADMIN;

        if (usuario.getRole().equals(roleadmin)) {

            return true;

        }

        return false;

    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioActualizado) {
        Usuario usuario = usuarioService.actualizarUsuario(id, usuarioActualizado);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{userid}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable int userid) {
        Usuario usuario = usuarioService.findById(userid);
        if (usuario != null) {

            List<Venta> ventasUser = usuario.getVentas();

            for (Venta v : ventasUser) {
                int vid = v.getVenta_id();

                ventaService.deleteVentaById(vid);

            }

            List<Libro> librosUser = usuario.getLibrosPublicados();
            for (Libro l : librosUser) {

                int lid = l.getLibro_id();
                libroService.deleteLibroById(lid);
            }

            usuarioService.deleteUsuarioById(userid);
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> actualizarParcialmenteUsuario(@PathVariable int id,
            @RequestBody Usuario usuarioActualizado) {
        Usuario usuario = usuarioService.actualizarParcialmenteUsuario(id, usuarioActualizado);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/rol/{id}")
    public ResponseEntity<Role> actualizarRol(@PathVariable int id) {

        Usuario usuario = usuarioService.findById(id);

        if (usuario != null) {
            Role rol = usuarioService.rolechange(id);
            return ResponseEntity.ok(rol);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscarId")
    public ResponseEntity<?> obtenerIdUsuarioPorNombre(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            authorizationHeader = authorizationHeader.substring(7); // Eliminar "Bearer "
            int idusuario = jwts.extractId(authorizationHeader);
            return ResponseEntity.ok(Map.of("id", idusuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el ID del usuario");
        }
    }
}
