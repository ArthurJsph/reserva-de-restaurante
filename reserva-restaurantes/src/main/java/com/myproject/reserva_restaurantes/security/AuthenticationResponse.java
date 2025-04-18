package com.myproject.reserva_restaurantes.security;

import com.myproject.reserva_restaurantes.dto.UsuarioResponseDTO;
import lombok.Getter;

@Getter
public class AuthenticationResponse {

    private final String jwt;
    private UsuarioResponseDTO usuario;

    public AuthenticationResponse(String jwt, UsuarioResponseDTO usuario) {
        this.jwt = jwt;
        this.usuario = usuario;
    }

}
