package com.AlkemyChallange.Disney.controllers;

import com.AlkemyChallange.Disney.dto.PersonajeDTO;
import com.AlkemyChallange.Disney.dto.PersonajeINF;
import com.AlkemyChallange.Disney.services.PersonajeService;
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
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired
    PersonajeService personajeService;

    @PostMapping()
    public PersonajeDTO crear(@RequestParam("img") MultipartFile imagen, @ModelAttribute PersonajeDTO dto) throws Exception {
        return personajeService.guardar(dto, imagen);
    }

    @GetMapping()
    public List<PersonajeINF> obtenerPersonajes() throws Exception {
        return personajeService.obtenerPersonajes();
    }

    @GetMapping("/{id}")
    public PersonajeDTO buscarPorId(@PathVariable("id") Integer id) throws Exception {
        return this.personajeService.buscarPorId(id);
    }

    @GetMapping(params = "name")
    public PersonajeDTO buscarPorNombre(@RequestParam("name") String nombre) throws Exception {
        return personajeService.buscarPorNombre(nombre);
    }

    @GetMapping(params = "age")
    public List<PersonajeDTO> buscarPorEdad(@RequestParam("age") Integer edad) throws Exception {
        return personajeService.buscarPorEdad(edad);
    }

    @DeleteMapping("delete/{id}")
    public String eliminar(@PathVariable("id") Integer id) throws Exception {
        try {
            return this.personajeService.eliminar(id);
        } catch (Exception e) {
            return "El personaje no pudo ser eliminado";
        }
    }
}
