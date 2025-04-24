package com.myproject.reserva_restaurantes.security;

import com.myproject.reserva_restaurantes.dto.UsuarioResponseDTO;



public class AuthenticationResponse {

    private final String jwt;
    private UsuarioResponseDTO usuario;

    public String getJwt() {
        return jwt;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public AuthenticationResponse(String jwt, UsuarioResponseDTO usuario) {
        this.jwt = jwt;
        this.usuario = usuario;
    }



}
