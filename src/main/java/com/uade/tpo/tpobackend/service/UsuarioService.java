// UsuarioService.java
package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Usuario;

public interface UsuarioService {
    Usuario findById(int id);
    Usuario findByNombre(String nombre);
}
