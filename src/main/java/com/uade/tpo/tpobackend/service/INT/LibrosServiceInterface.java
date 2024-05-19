package com.uade.tpo.tpobackend.service.INT;

import com.uade.tpo.tpobackend.entity.Libro;
import java.util.ArrayList;



public interface LibrosServiceInterface {

    public ArrayList<Libro> getLibros();
    
    public Libro getLibrosById(int libroId);
    
    public Libro createLibro(Libro entity);


}
