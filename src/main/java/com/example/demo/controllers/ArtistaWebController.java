package com.example.demo.controllers;

import com.example.demo.models.Artista;
import com.example.demo.services.ServicioMusica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web/artistas")
public class ArtistaWebController {

    @Autowired
    private ServicioMusica servicioMusica;

    @GetMapping
    public String listarArtistas(@RequestParam(required = false) String genero, Model model) {
        if (genero != null && !genero.isBlank()) {
            model.addAttribute("artistas", servicioMusica.filtrarArtistasPorGenero(genero));
            model.addAttribute("genero", genero);
        } else {
            model.addAttribute("artistas", servicioMusica.listarArtistas());
        }
        return "artistas/lista";
    }

    @GetMapping("/nueva")
    public String nuevaArtistaForm(Model model) {
        model.addAttribute("artista", new Artista());
        return "artistas/nueva";
    }

    @PostMapping("/nueva")
    public String crearArtista(Artista artista) {
        servicioMusica.crearArtista(artista);
        return "redirect:/web/artistas";
    }

    @GetMapping("/editar/{id}")
    public String editarArtistaForm(@PathVariable Long id, Model model) {
        Artista artista = servicioMusica.obtenerArtistaPorId(id).orElse(new Artista());
        model.addAttribute("artista", artista);
        return "artistas/editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizarArtista(@PathVariable Long id, Artista artista) {
        servicioMusica.actualizarArtista(id, artista);
        return "redirect:/web/artistas";
    }

    @PostMapping("/borrar/{id}")
    public String borrarArtista(@PathVariable Long id) {
        servicioMusica.borrarArtista(id);
        return "redirect:/web/artistas";
    }

    @GetMapping("/{id}")
    public String detalleArtista(@PathVariable Long id, Model model) {
        model.addAttribute("artista", servicioMusica.obtenerArtistaPorId(id).orElse(null));
        model.addAttribute("albumes", servicioMusica.listarAlbumesPorArtistaOrdenados(id));
        return "artistas/detalle";
    }
}
