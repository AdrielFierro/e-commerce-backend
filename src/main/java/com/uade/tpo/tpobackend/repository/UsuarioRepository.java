// UsuarioRepository.java
package com.uade.tpo.tpobackend.repository;

import com.uade.tpo.tpobackend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByNombre(String nombre);
    
}
