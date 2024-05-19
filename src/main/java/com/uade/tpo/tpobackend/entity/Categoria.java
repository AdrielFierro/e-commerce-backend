package com.uade.tpo.tpobackend.entity;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data

public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String descripcion;
    @Column
    private double precio;

    public Categoria(int id,String descripcion,double precio){
    this.id=id;
    this.descripcion=descripcion;
    this.precio=precio;
    }

    public int getId(){
        return id;
    }



    
    public Map<String, Object> obtenerDatos() {
        Map<String, Object> datos = new HashMap<>();
        datos.put("id", id);
        datos.put("descripcion", descripcion);
        datos.put("precio", precio);
        return datos;
    }
}
