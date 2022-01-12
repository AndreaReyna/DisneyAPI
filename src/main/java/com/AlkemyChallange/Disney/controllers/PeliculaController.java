package com.AlkemyChallange.Disney.controllers;

import com.AlkemyChallange.Disney.dto.PeliculaDTO;
import com.AlkemyChallange.Disney.dto.PeliculaINF;
import com.AlkemyChallange.Disney.services.PeliculaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/movies")
public class PeliculaController {

    @Autowired
    PeliculaService peliculaService;

    @PostMapping()
    public PeliculaDTO crear(@RequestParam("img") MultipartFile imagen, @ModelAttribute PeliculaDTO dto) throws Exception {
        return peliculaService.guardar(dto, imagen);
    }

    @GetMapping()
    public List<PeliculaINF> obtenerPeliculas() throws Exception {
        return peliculaService.obtenerPeliculas();
    }

    @GetMapping("/{id}")
    public PeliculaDTO buscarPorId(@PathVariable("id") Integer id) throws Exception {
        return this.peliculaService.buscarPorId(id);
    }

    @GetMapping(params = "name")
    public PeliculaDTO buscarPorNombre(@RequestParam("name") String nombre) throws Exception {
        return peliculaService.buscarPorTitulo(nombre);
    }

    @GetMapping(params = "genre")
    public List<PeliculaDTO> buscarPorNombre(@RequestParam("genre") Integer genero) throws Exception {
        return peliculaService.buscarPorGenero(genero);
    }

    @GetMapping(params = "order")
    public List<PeliculaINF> listaOrdenada(@RequestParam("order") String order) throws Exception {
        return peliculaService.listaOrdenada(order);
    }

    @DeleteMapping("delete/{id}")
    public String eliminar(@PathVariable("id") Integer id) throws Exception {
        try {
            return this.peliculaService.eliminar(id);
        } catch (Exception e) {
            return "La pelicula no pudo ser eliminada";
        }
    }
}
