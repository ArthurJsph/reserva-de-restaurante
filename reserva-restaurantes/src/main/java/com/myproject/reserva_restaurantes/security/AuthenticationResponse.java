package com.myproject.reserva_restaurantes.security;

import com.myproject.reserva_restaurantes.dto.response.UsuarioResponse;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class AuthenticationResponse {

    private final String jwt;
    private UsuarioResponse usuario;

}
