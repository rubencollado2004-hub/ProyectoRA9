package com.example.demo.controllers;

import com.example.demo.models.Album;
import com.example.demo.services.ServicioMusica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/albumes")
public class AlbumApiController {

    @Autowired
    private ServicioMusica servicioMusica;

    @PostMapping
    public Album crearAlbum(@RequestBody Album album) {
        return servicioMusica.crearAlbum(album);
    }

    @GetMapping
    public Iterable<Album> listarAlbumes() {
        return servicioMusica.listarAlbumes();
    }

    @GetMapping("/{id}")
    public Album obtenerAlbum(@PathVariable Long id) {
        return servicioMusica.obtenerAlbumPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Álbum no encontrado"));
    }

    @PutMapping("/{id}")
    public Album actualizarAlbum(@PathVariable Long id, @RequestBody Album album) {
        return servicioMusica.actualizarAlbum(id, album);
    }

    @RequestMapping(value = "/{id}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
    public void borrarAlbum(@PathVariable Long id) {
        servicioMusica.borrarAlbum(id);
    }

    @GetMapping("/artista/{artistaId}")
    public Iterable<Album> listarAlbumesPorArtista(@PathVariable Long artistaId) {
        return servicioMusica.listarAlbumesPorArtistaOrdenados(artistaId);
    }

    @GetMapping("/rango")
    public Iterable<Album> buscarAlbumesEnRangoAños(@RequestParam("añoInicio") int añoInicio,
                                                    @RequestParam("añoFin") int añoFin) {
        return servicioMusica.buscarAlbumesEnRangoAños(añoInicio, añoFin);
    }

    @RequestMapping(value = "/anteriores/{año}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
    public void borrarAlbumesAnteriores(@PathVariable int año) {
        servicioMusica.borrarAlbumesAnterioresA(año);
    }
}
