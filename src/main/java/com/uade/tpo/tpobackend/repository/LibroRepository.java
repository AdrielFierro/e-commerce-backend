package com.uade.tpo.tpobackend.repository;

import com.uade.tpo.tpobackend.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    
}
