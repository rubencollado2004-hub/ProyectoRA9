package com.example.demo.repositories;

import com.example.demo.models.Artista;
import org.springframework.data.repository.CrudRepository;

public interface ArtistaRepository extends CrudRepository<Artista, Long> {
    Iterable<Artista> findByGenero(String genero);
}
