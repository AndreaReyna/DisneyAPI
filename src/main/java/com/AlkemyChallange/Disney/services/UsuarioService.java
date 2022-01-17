package com.AlkemyChallange.Disney.services;

import com.AlkemyChallange.Disney.dto.UsuarioDTO;
import com.AlkemyChallange.Disney.entities.Usuario;
import com.AlkemyChallange.Disney.enums.Rol;
import com.AlkemyChallange.Disney.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username);
    }

    @Transactional
    public String crear(UsuarioDTO dto) throws Exception {
        validaciones(dto);

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setEmail(dto.getEmail());
        usuario.setClave(encoder.encode(dto.getClave()));
        if (usuarioRepository.findAll().isEmpty()) {
            usuario.setRol(Rol.ADMIN);
        } else {
            usuario.setRol(Rol.USER);
        }
        usuarioRepository.save(usuario);
        emailService.send(dto.getEmail());

        return "Registro exitoso";
    }

    @Transactional
    public String eliminar(Integer id) {
        try {
            usuarioRepository.deleteById(id);
            return "Usuario eliminado con exito";
        } catch (Exception e) {
            throw e;
        }
    }

    public void validaciones(UsuarioDTO dto) throws Exception {
        if (dto.getEmail() == null) {
            throw new Exception("Debe ingresar un correo");
        }
        if (usuarioRepository.existsUsuarioByEmail(dto.getEmail())) {
            throw new Exception("El email ya se encuentra registrado");
        }
        if (!(dto.getEmail().contains("@") && dto.getEmail().contains(".com"))) {
            throw new Exception("Debe ingresar un formato de email valido.");
        }
        if (usuarioRepository.findByUsername(dto.getUsername()) != null) {
            throw new Exception("Ese nombre de usuario ya se encuentra registrado");
        }
        if (dto.getClave() == null) {
            throw new Exception("Debe ingresar una contraseña");
        }
        if (dto.getClave().length() < 6) {
            throw new Exception("La contraseña debe tener al menos 6 caracteres");
        }
    }
}
