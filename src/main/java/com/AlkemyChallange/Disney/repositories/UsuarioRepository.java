package com.AlkemyChallange.Disney.repositories;

import com.AlkemyChallange.Disney.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByUsername(String username);

    boolean existsUsuarioByUsername(String username);

    boolean existsUsuarioByEmail(String email);

}
