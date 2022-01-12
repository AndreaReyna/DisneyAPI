package com.AlkemyChallange.Disney.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaINF implements Serializable {

    private String titulo;
    private String imagen;
    private LocalDate fechaCreacion;
}
