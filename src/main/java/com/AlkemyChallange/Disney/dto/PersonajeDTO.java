package com.AlkemyChallange.Disney.dto;

import com.AlkemyChallange.Disney.entities.Pelicula;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonajeDTO implements Serializable {

    private Integer id;
    private String nombre;
    private String imagen;
    private Integer edad;
    private Double peso;
    private String historia;

    private List<Pelicula> peliculas = new ArrayList<>();
}
