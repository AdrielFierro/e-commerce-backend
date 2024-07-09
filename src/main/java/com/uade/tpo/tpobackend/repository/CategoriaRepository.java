// CategoriaRepository.java
package com.uade.tpo.tpobackend.repository;

import com.uade.tpo.tpobackend.entity.Categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Query(value = "select c from Categoria c  where c.descripcion = ?1")
    List<Categoria> findByDescripcion(String descripcion);

    @Query(value = "select c from Categoria c  where c.nombre = ?1")
    Optional<Categoria> findByNombre(String nombre);

}
