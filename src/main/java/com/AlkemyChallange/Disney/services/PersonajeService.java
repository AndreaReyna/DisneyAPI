package com.AlkemyChallange.Disney.services;

import com.AlkemyChallange.Disney.dto.PersonajeDTO;
import com.AlkemyChallange.Disney.dto.PersonajeINF;
import com.AlkemyChallange.Disney.entities.Personaje;
import com.AlkemyChallange.Disney.repositories.PeliculaRepository;
import com.AlkemyChallange.Disney.repositories.PersonajeRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PersonajeService {

    @Autowired
    PersonajeRepository personajeRepository;

    @Autowired
    PeliculaRepository peliculaRepository;

    @Autowired
    ImagenService imagenService;

    @Transactional
    public PersonajeDTO guardar(PersonajeDTO dto, MultipartFile imagen) throws Exception {
        try {
            validaciones(dto);
            ModelMapper mp = new ModelMapper();
            Personaje personaje = mp.map(dto, Personaje.class);

            if (imagen.isEmpty()) {
                throw new Exception("Debe ingresar una imagen");
            }
            personaje.setImagen(imagenService.copiar(imagen));
            dto.setImagen(imagenService.copiar(imagen));

            personajeRepository.save(personaje);
            dto.setId(personaje.getId());
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<PersonajeINF> obtenerPersonajes() throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            List<PersonajeINF> dto = new ArrayList();
            List<Personaje> personajes = personajeRepository.findAll();
            if (personajes.isEmpty()) {
                throw new Exception("No hay personajes cargados");
            }
            for (Personaje p : personajes) {
                dto.add(mp.map(p, PersonajeINF.class));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PersonajeDTO buscarPorId(Integer id) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            Personaje p = personajeRepository.findById(id).orElse(null);
            if (p == null) {
                throw new Exception("No existe un personaje asociado a ese id");
            }
            PersonajeDTO dto = mp.map(p, PersonajeDTO.class);
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PersonajeDTO buscarPorNombre(String nombre) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            PersonajeDTO dto = new PersonajeDTO();
            Personaje p = personajeRepository.buscarNombre(nombre);
            if (p == null) {
                throw new Exception("No se encuentran personajes con ese nombre");
            }
            dto = mp.map(p, PersonajeDTO.class);
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<PersonajeDTO> buscarPorEdad(Integer edad) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            List<PersonajeDTO> dto = new ArrayList();
            List<Personaje> personajes = personajeRepository.buscarEdad(edad);
            if (personajes.isEmpty()) {
                throw new Exception("No hay personajes con esa edad");
            }
            for (Personaje p : personajes) {
                dto.add(mp.map(p, PersonajeDTO.class));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public String eliminar(Integer id) {
        try {
            personajeRepository.deleteById(id);
            return "Personaje eliminado con exito";
        } catch (Exception e) {
            throw e;
        }
    }

    public void validaciones(PersonajeDTO dto) throws Exception {
        if (dto.getNombre() == null) {
            throw new Exception("El nombre no puede estar vacio");
        }
        if (personajeRepository.buscarNombre(dto.getNombre()) != null) {
            throw new Exception("Ya existe un personaje con ese nombre");
        }
        if (dto.getEdad() == null) {
            throw new Exception("La edad del personaje no puede estar vacia");
        }
        if (dto.getHistoria() == null) {
            throw new Exception("La historia del personaje no puede estar vacia");
        }
        if (dto.getPeso() == null) {
            throw new Exception("El peso del personaje no puede estar vacio");
        }
    }
}
