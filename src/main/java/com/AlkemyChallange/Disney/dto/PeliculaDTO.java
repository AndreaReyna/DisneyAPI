package com.AlkemyChallange.Disney.dto;

import com.AlkemyChallange.Disney.entities.Genero;
import com.AlkemyChallange.Disney.entities.Personaje;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaDTO implements Serializable {

    private Integer id;
    private String titulo;
    private String imagen;
    private LocalDate fechaCreacion;
    private Integer calificacion;
    private List<Personaje> personajes = new ArrayList<>();
    private Genero genero;
}
