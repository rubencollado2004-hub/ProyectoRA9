package com.example.demo.controllers;

import com.example.demo.models.Album;
import com.example.demo.models.Artista;
import com.example.demo.services.ServicioMusica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/albumes")
public class AlbumWebController {

    @Autowired
    private ServicioMusica servicioMusica;

    @GetMapping
    public String listarAlbumes(Model model) {
        model.addAttribute("albumes", servicioMusica.listarAlbumes());
        return "albumes/lista";
    }

    @GetMapping("/nueva")
    public String nuevoAlbumForm(Model model) {
        Album album = new Album();
        album.setArtista(new Artista());
        model.addAttribute("album", album);
        model.addAttribute("artistas", servicioMusica.listarArtistas());
        return "albumes/nueva";
    }

    @PostMapping("/nueva")
    public String crearAlbum(Album album) {
        servicioMusica.crearAlbum(album);
        return "redirect:/web/albumes";
    }

    @GetMapping("/editar/{id}")
    public String editarAlbumForm(@PathVariable Long id, Model model) {
        Album album = servicioMusica.obtenerAlbumPorId(id).orElse(new Album());
        if (album.getArtista() == null) {
            album.setArtista(new Artista());
        }
        model.addAttribute("album", album);
        model.addAttribute("artistas", servicioMusica.listarArtistas());
        return "albumes/editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizarAlbum(@PathVariable Long id, Album album) {
        servicioMusica.actualizarAlbum(id, album);
        return "redirect:/web/albumes";
    }

    @PostMapping("/borrar/{id}")
    public String borrarAlbum(@PathVariable Long id) {
        servicioMusica.borrarAlbum(id);
        return "redirect:/web/albumes";
    }

    @GetMapping("/{id}")
    public String detalleAlbum(@PathVariable Long id, Model model) {
        model.addAttribute("album", servicioMusica.obtenerAlbumPorId(id).orElse(null));
        return "albumes/detalle";
    }
}
