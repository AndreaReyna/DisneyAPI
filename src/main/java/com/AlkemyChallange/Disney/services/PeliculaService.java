package com.AlkemyChallange.Disney.services;

import com.AlkemyChallange.Disney.dto.PeliculaDTO;
import com.AlkemyChallange.Disney.dto.PeliculaINF;
import com.AlkemyChallange.Disney.entities.Genero;
import com.AlkemyChallange.Disney.entities.Pelicula;
import com.AlkemyChallange.Disney.entities.Personaje;
import com.AlkemyChallange.Disney.repositories.PeliculaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PeliculaService {

    @Autowired
    PeliculaRepository peliculaRepository;

    @Autowired
    PersonajeService personajeService;

    @Autowired
    GeneroService generoService;

    @Autowired
    ImagenService imagenService;

    public PeliculaDTO guardar(PeliculaDTO dto, MultipartFile imagen) throws Exception {
        try {
            validaciones(dto);

            ModelMapper mp = new ModelMapper();

            Pelicula pelicula = mp.map(dto, Pelicula.class);

            if (imagen.isEmpty()) {
                throw new Exception("Debe ingresar una imagen");
            }
            pelicula.setImagen(imagenService.copiar(imagen));
            dto.setImagen(imagenService.copiar(imagen));

            peliculaRepository.save(pelicula);

            dto.setId(pelicula.getId());

            if (!pelicula.getPersonajes().isEmpty()) {
                List<Personaje> lista = new ArrayList();
                for (Personaje p : pelicula.getPersonajes()) {
                    if (p != null) {
                        lista.add(mp.map(personajeService.buscarPorId(p.getId()), Personaje.class));
                    }
                }
                dto.setPersonajes(lista);
            }
            dto.setGenero(mp.map(generoService.buscarPorId(pelicula.getGenero().getId()), Genero.class));
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<PeliculaINF> obtenerPeliculas() throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            List<PeliculaINF> dto = new ArrayList();
            List<Pelicula> peliculas = peliculaRepository.findAll();
            if (peliculas.isEmpty()) {
                throw new Exception("No hay peliculas cargadas");
            }
            for (Pelicula p : peliculas) {
                dto.add(mp.map(p, PeliculaINF.class
                ));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<PeliculaINF> listaOrdenada(String order) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            List<PeliculaINF> dto = new ArrayList();
            List<Pelicula> peliculas = (order.equals("DESC")) ? peliculaRepository.listaDesc() : peliculaRepository.listaAsc();
            if (peliculas.isEmpty()) {
                throw new Exception("No hay peliculas cargadas");
            }
            for (Pelicula p : peliculas) {
                dto.add(mp.map(p, PeliculaINF.class
                ));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PeliculaDTO buscarPorId(Integer id) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            Pelicula p = peliculaRepository.findById(id).orElse(null);
            if (p == null) {
                throw new Exception("No existe una pelicula asociada a ese id");
            }
            PeliculaDTO dto = mp.map(p, PeliculaDTO.class
            );
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PeliculaDTO buscarPorTitulo(String nombre) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            PeliculaDTO dto = new PeliculaDTO();
            Pelicula p = peliculaRepository.buscarPorTitulo(nombre);
            if (p == null) {
                throw new Exception("No se encuentran peliculas con ese titulo");
            }
            dto = mp.map(p, PeliculaDTO.class
            );
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<PeliculaDTO> buscarPorGenero(Integer genero) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            List<PeliculaDTO> dto = new ArrayList();
            List<Pelicula> peliculas = peliculaRepository.buscarPorGenero(genero);
            if (peliculas.isEmpty()) {
                throw new Exception("No hay peliculas con ese genero");
            }
            for (Pelicula p : peliculas) {
                dto.add(mp.map(p, PeliculaDTO.class
                ));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public String eliminar(Integer id) {
        try {
            peliculaRepository.deleteById(id);
            return "Pelicula eliminada con exito";
        } catch (Exception e) {
            throw e;
        }
    }

    public void validaciones(PeliculaDTO dto) throws Exception {
        if (personajeService.obtenerPersonajes().isEmpty()) {
            throw new Exception("Deben existir personajes para crear la pelicula");
        }
        if (dto.getPersonajes().isEmpty()) {
            throw new Exception("La pelicula debe tener al menos un personaje");
        }
        if (dto.getTitulo() == null) {
            throw new Exception("La pelicula debe tener un titulo");
        }
        if (peliculaRepository.buscarPorTitulo(dto.getTitulo()) != null) {
            throw new Exception("Ya existe una pelicula con ese titulo");
        }
        if (generoService.obtenerGeneros().isEmpty()) {
            throw new Exception("Deben existir generos para crear la pelicula");
        }
        if (dto.getGenero() == null) {
            throw new Exception("La pelicula debe tener un genero");
        }
        if (dto.getFechaCreacion() == null) {
            throw new Exception("La pelicula debe tener una fecha de creación");
        }
        if (dto.getCalificacion() == null) {
            throw new Exception("La pelicula debe tener una calificación");
        }
    }
}
