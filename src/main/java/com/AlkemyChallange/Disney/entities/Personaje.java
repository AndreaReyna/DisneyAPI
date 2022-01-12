package com.AlkemyChallange.Disney.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String nombre;
    private String imagen;
    private Integer edad;
    private Double peso;
    private String historia;

    @JsonIgnore
    @ManyToMany(mappedBy = "personajes")
    private List<Pelicula> peliculas;

}
