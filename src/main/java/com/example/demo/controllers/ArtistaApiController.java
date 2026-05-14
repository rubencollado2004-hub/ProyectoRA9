package com.example.demo.controllers;

import com.example.demo.models.Artista;
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
@RequestMapping("/api/artistas")
public class ArtistaApiController {

    @Autowired
    private ServicioMusica servicioMusica;

    @PostMapping
    public Artista crearArtista(@RequestBody Artista artista) {
        return servicioMusica.crearArtista(artista);
    }

    @GetMapping
    public Iterable<Artista> listarArtistas(@RequestParam(required = false) String genero) {
        if (genero != null && !genero.isBlank()) {
            return servicioMusica.filtrarArtistasPorGenero(genero);
        }
        return servicioMusica.listarArtistas();
    }

    @GetMapping("/{id}")
    public Artista obtenerArtista(@PathVariable Long id) {
        return servicioMusica.obtenerArtistaPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista no encontrado"));
    }

    @PutMapping("/{id}")
    public Artista actualizarArtista(@PathVariable Long id, @RequestBody Artista artista) {
        return servicioMusica.actualizarArtista(id, artista);
    }

    @RequestMapping(value = "/{id}", method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
    public void borrarArtista(@PathVariable Long id) {
        servicioMusica.borrarArtista(id);
    }
}
