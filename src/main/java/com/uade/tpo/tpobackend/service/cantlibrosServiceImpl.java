package com.uade.tpo.tpobackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.tpobackend.entity.cantlibros;
import com.uade.tpo.tpobackend.repository.cantlibrosRepository;

import java.util.*;

@Service
public class cantlibrosServiceImpl implements cantlibrosService {

    @Autowired
    cantlibrosRepository cantlibrosRepository;

    @Override
    public cantlibros crearCantLibros(cantlibros cantaux) {

        return cantlibrosRepository.save(cantaux);
    }

}
