package com.AlkemyChallange.Disney.repositories;

import com.AlkemyChallange.Disney.entities.Personaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {

    @Query("SELECT p FROM Personaje p WHERE p.nombre = :nombre")
    Personaje buscarNombre(@Param("nombre") String nombre);

    @Query("SELECT nombre, imagen FROM Personaje")
    List<Personaje> buscarNombreImagen();

    @Query("SELECT p FROM Personaje p WHERE p.edad = :edad")
    List<Personaje> buscarEdad(@Param("edad") Integer edad);

}
