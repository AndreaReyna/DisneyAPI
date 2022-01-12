package com.AlkemyChallange.Disney.repositories;

import com.AlkemyChallange.Disney.entities.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    @Query("SELECT g FROM Genero g WHERE g.nombre = :nombre")
    Genero buscarPorNombre(@Param("nombre") String nombre);
}
