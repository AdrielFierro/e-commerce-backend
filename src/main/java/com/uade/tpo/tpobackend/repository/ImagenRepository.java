package com.uade.tpo.tpobackend.repository;


import com.uade.tpo.tpobackend.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {
    // Puedes agregar consultas personalizadas si es necesario
}
