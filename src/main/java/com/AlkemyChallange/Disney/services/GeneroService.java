package com.AlkemyChallange.Disney.services;

import com.AlkemyChallange.Disney.dto.GeneroDTO;
import com.AlkemyChallange.Disney.entities.Genero;
import com.AlkemyChallange.Disney.repositories.GeneroRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GeneroService {

    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    ImagenService imagenService;

    public GeneroDTO guardar(GeneroDTO dto, MultipartFile imagen) throws Exception {
        ModelMapper mp = new ModelMapper();
        if (dto.getNombre() == null) {
            throw new Exception("El genero debe tener un nombre");
        }
        if (generoRepository.buscarPorNombre(dto.getNombre()) != null) {
            throw new Exception("Ya existe un genero con ese nombre");
        }

        Genero genero = mp.map(dto, Genero.class);

        if (!imagen.isEmpty()) {
            genero.setImagen(imagenService.copiar(imagen));
            dto.setImagen(imagenService.copiar(imagen));
        }
        generoRepository.save(genero);
        dto.setId(genero.getId());
        return dto;
    }

    @Transactional(readOnly = true)
    public List<GeneroDTO> obtenerGeneros() throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            List<GeneroDTO> dto = new ArrayList();
            List<Genero> generos = generoRepository.findAll();
            if (generos.isEmpty()) {
                throw new Exception("No hay generos cargados");
            }
            for (Genero g : generos) {
                dto.add(mp.map(g, GeneroDTO.class));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    public GeneroDTO buscarPorId(Integer id) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            Genero g = generoRepository.findById(id).orElse(null);
            if (g == null) {
                throw new Exception("No existe un genero asociado a ese id");
            }
            GeneroDTO dto = mp.map(g, GeneroDTO.class);
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public GeneroDTO buscarPorNombre(String nombre) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            GeneroDTO dto = new GeneroDTO();
            Genero g = generoRepository.buscarPorNombre(nombre);
            if (g == null) {
                throw new Exception("No se encuentran generos con ese nombre");
            }
            dto = mp.map(g, GeneroDTO.class);
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public String eliminar(Integer id) {
        try {
            generoRepository.deleteById(id);
            return "Genero eliminado con exito";
        } catch (Exception e) {
            throw e;
        }
    }
}
