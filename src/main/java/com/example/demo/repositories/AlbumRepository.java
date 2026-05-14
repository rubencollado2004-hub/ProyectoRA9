package com.example.demo.repositories;

import com.example.demo.models.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    Iterable<Album> findByArtistaIdOrderByAñoAsc(Long artistaId);
    Iterable<Album> findByAñoBetween(int añoInicio, int añoFin);
    void deleteByAñoBefore(int año);
}
