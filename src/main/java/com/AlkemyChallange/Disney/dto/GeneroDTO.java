package com.AlkemyChallange.Disney.dto;

import com.AlkemyChallange.Disney.entities.Pelicula;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneroDTO implements Serializable {

    private Integer id;
    private String nombre;
    private String imagen;
    private List<Pelicula> peliculas;
}
