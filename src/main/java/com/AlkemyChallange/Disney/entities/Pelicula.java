package com.AlkemyChallange.Disney.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String titulo;
    private String imagen;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    @Column(nullable = false)
    private Integer calificacion;

    @ManyToMany
    @JoinTable(name = "peliculas_personajes",
            joinColumns = {
                @JoinColumn(name = "pelicula_id", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "personaje_id", nullable = false)})
    private List<Personaje> personajes;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

}
