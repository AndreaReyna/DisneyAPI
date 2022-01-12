package com.AlkemyChallange.Disney.repositories;

import com.AlkemyChallange.Disney.entities.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {

    @Query("SELECT p FROM Pelicula p WHERE p.titulo = :titulo")
    Pelicula buscarPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT p FROM Pelicula p WHERE p.genero.id = :genero")
    List<Pelicula> buscarPorGenero(@Param("genero") Integer genero);

    @Query("SELECT p FROM Pelicula p ORDER BY p.fechaCreacion")
    List<Pelicula> listaAsc();

    @Query("SELECT p FROM Pelicula p ORDER BY p.fechaCreacion DESC")
    List<Pelicula> listaDesc();
}
