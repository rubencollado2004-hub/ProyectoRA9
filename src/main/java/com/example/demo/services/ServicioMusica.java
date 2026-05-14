package com.example.demo.services;

import com.example.demo.models.Album;
import com.example.demo.models.Artista;
import com.example.demo.repositories.AlbumRepository;
import com.example.demo.repositories.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioMusica {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public Artista crearArtista(Artista artista) {
        return artistaRepository.save(artista);
    }

    public Iterable<Artista> listarArtistas() {
        return artistaRepository.findAll();
    }

    public Optional<Artista> obtenerArtistaPorId(Long id) {
        return artistaRepository.findById(id);
    }

    public Artista actualizarArtista(Long id, Artista artista) {
        Artista existente = artistaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artista no encontrado"));
        existente.setNombre(artista.getNombre());
        existente.setGenero(artista.getGenero());
        existente.setPais(artista.getPais());
        existente.setAñoDebut(artista.getAñoDebut());
        return artistaRepository.save(existente);
    }

    public void borrarArtista(Long id) {
        artistaRepository.deleteById(id);
    }

    public Album crearAlbum(Album album) {
        if (album.getArtista() != null && album.getArtista().getId() != null) {
            artistaRepository.findById(album.getArtista().getId()).ifPresent(album::setArtista);
        }
        return albumRepository.save(album);
    }

    public Iterable<Album> listarAlbumes() {
        return albumRepository.findAll();
    }

    public Optional<Album> obtenerAlbumPorId(Long id) {
        return albumRepository.findById(id);
    }

    public Album actualizarAlbum(Long id, Album album) {
        Album existente = albumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Álbum no encontrado"));
        existente.setTitulo(album.getTitulo());
        existente.setAño(album.getAño());
        existente.setNumeroPistas(album.getNumeroPistas());
        existente.setDuracionTotal(album.getDuracionTotal());
        if (album.getArtista() != null && album.getArtista().getId() != null) {
            artistaRepository.findById(album.getArtista().getId()).ifPresent(existente::setArtista);
        }
        return albumRepository.save(existente);
    }

    public void borrarAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    public Iterable<Album> listarAlbumesPorArtistaOrdenados(Long artistaId) {
        return albumRepository.findByArtistaIdOrderByAñoAsc(artistaId);
    }

    public Iterable<Artista> filtrarArtistasPorGenero(String genero) {
        return artistaRepository.findByGenero(genero);
    }

    public Iterable<Album> buscarAlbumesEnRangoAños(int añoInicio, int añoFin) {
        return albumRepository.findByAñoBetween(añoInicio, añoFin);
    }

    public void borrarAlbumesAnterioresA(int año) {
        albumRepository.deleteByAñoBefore(año);
    }
}
