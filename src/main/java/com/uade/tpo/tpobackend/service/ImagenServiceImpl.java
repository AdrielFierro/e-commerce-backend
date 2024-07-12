package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Imagen;
import com.uade.tpo.tpobackend.repository.ImagenRepository;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServiceImpl implements ImagenService {

    @Autowired
    private final ImagenRepository imagenRepository;

    public ImagenServiceImpl(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    public Imagen guardarImagen(MultipartFile archivo) {
        Imagen imagen = new Imagen();
        try {
            imagen.setDatosImagen(archivo.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // Set the byte array of image data
        return imagenRepository.save(imagen); // Save and return the persisted entity
    }

    @Override
    public Imagen obtenerImagen(Long id) {
        return imagenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));
    }

    @Override
    public void cambiarImagen(MultipartFile datosImagenNueva, Long id) {
        try {
            Imagen imagen = imagenRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));
            ;
            imagen.setDatosImagen(datosImagenNueva.getBytes());
            imagenRepository.save(imagen);

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
