package com.uade.tpo.tpobackend.service;

import com.uade.tpo.tpobackend.entity.Libro;
import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.exceptions.LibroInexistenteException;
// import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository LibroRepository;

    @Override
    public Libro actualizarLibro(int libro_id, Libro LibroActualizado) {
        Optional<Libro> LibroOptional = LibroRepository.findById(libro_id);
        if (LibroOptional.isPresent()) {
            Libro libro = LibroOptional.get();
            libro.setNombre(LibroActualizado.getNombre());
            libro.setDescripcion(LibroActualizado.getDescripcion());
            libro.setPrecio(LibroActualizado.getPrecio());
            libro.setAutor(LibroActualizado.getAutor());
            libro.setCate(LibroActualizado.getCate());
            libro.setStock(LibroActualizado.getStock());

            return LibroRepository.save(libro);
        }
        return null;
    }

    @Override
    public List<Libro> getLibros() {
        return LibroRepository.findAll();
    }

    @Override
    public Libro getLibroById(int libroId) {
        return LibroRepository.findById(libroId).orElse(null);
    }

    @Override
    public Libro createLibro(Libro libro) {
        return LibroRepository.save(libro);
    }

    @Override
    public void deleteLibroById(int libroId) {

        LibroRepository.deleteById(libroId);
        ;
    }

    @Override
    public Usuario getDuenio(int libro_id) {

        Libro libro = getLibroById(libro_id);

        return libro.getDuenio();
    }

    @Override
    public void venderLibros(int libroid, int cantArestar) {

        Optional<Libro> LibroOptional = LibroRepository.findById(libroid);
        if (LibroOptional.isPresent()) {
            Libro libro = LibroOptional.get();
            int stockLibro = libro.getStock();
            stockLibro = stockLibro - cantArestar;
            libro.setStock(stockLibro);
            LibroRepository.save(libro);
        }

    }

    @Override
    public int sumarStockLibro(int libro_id, int stockasumar) {

        Optional<Libro> LibroOptional = LibroRepository.findById(libro_id);
        Libro libro = LibroOptional.get();
        int stockLibro = libro.getStock();
        stockLibro = stockLibro + stockasumar;
        libro.setStock(stockLibro);
        LibroRepository.save(libro);
        return stockLibro;

    }

    @Override
    public void guardarImagen(int libro_id, MultipartFile file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardarImagen'");
    }

}
