package com.AlkemyChallange.Disney.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO implements Serializable {

    private String username;
    private String email;
    private String clave;
}
