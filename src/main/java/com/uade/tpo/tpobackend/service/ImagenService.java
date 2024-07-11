package com.uade.tpo.tpobackend.service;


import org.springframework.web.multipart.MultipartFile;

import com.uade.tpo.tpobackend.entity.Imagen;

public interface ImagenService {

    Imagen guardarImagen(MultipartFile datosImagen);

    Imagen obtenerImagen(Long id);

}
