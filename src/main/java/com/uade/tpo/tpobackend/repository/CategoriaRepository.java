// CategoriaRepository.java
package com.uade.tpo.tpobackend.repository;

import com.uade.tpo.tpobackend.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
