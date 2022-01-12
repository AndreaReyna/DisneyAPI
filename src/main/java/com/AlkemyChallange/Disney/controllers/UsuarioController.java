package com.AlkemyChallange.Disney.controllers;

import com.AlkemyChallange.Disney.dto.UsuarioDTO;
import com.AlkemyChallange.Disney.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/register")
@AllArgsConstructor
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    public String guardarUsuario(@RequestBody UsuarioDTO dto) throws Exception {
        return usuarioService.crear(dto);
    }
}
