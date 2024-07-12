package com.uade.tpo.tpobackend.controllers;

import com.uade.tpo.tpobackend.entity.*;
import com.uade.tpo.tpobackend.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/imagenes")
public class ImagenController {

    @Autowired
    private final ImagenService imagenService;

    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @PostMapping("/guardar")
    public Long guardarImagen(@RequestParam("datosImagen") MultipartFile datosImagen) {
        return imagenService.guardarImagen(datosImagen).getId();
    }

    @PutMapping("/cambiar/{id}")
    public void cambiarImagen(@RequestParam("datosImagen") MultipartFile datosImagen, @PathVariable Long id) {

        imagenService.cambiarImagen(datosImagen, id);

    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable Long id) {
        Imagen imagen = imagenService.obtenerImagen(id);
        return ResponseEntity.ok(imagen.getDatosImagen());
    }

    // Otros métodos del controlador según sea necesario
}
