package com.AlkemyChallange.Disney.controllers;

import com.AlkemyChallange.Disney.dto.GeneroDTO;
import com.AlkemyChallange.Disney.services.GeneroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/genres")
public class GeneroController {

    @Autowired
    GeneroService generoService;

    @PostMapping()
    public GeneroDTO crear(@RequestParam("img") MultipartFile imagen, @ModelAttribute GeneroDTO dto) throws Exception {
        return generoService.guardar(dto, imagen);
    }

    @GetMapping()
    public List<GeneroDTO> obtenerGeneros() throws Exception {
        return generoService.obtenerGeneros();
    }

    @GetMapping("/{id}")
    public GeneroDTO buscarPorId(@PathVariable("id") Integer id) throws Exception {
        return this.generoService.buscarPorId(id);
    }

    @DeleteMapping("delete/{id}")
    public String eliminarPorId(@PathVariable("id") Integer id) throws Exception {
        try {
            return this.generoService.eliminar(id);
        } catch (Exception e) {
            return "El genero no pudo ser eliminado";
        }
    }
}
