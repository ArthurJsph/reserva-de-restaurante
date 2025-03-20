package com.myproject.reserva_restaurantes.dto;


import com.myproject.reserva_restaurantes.Entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private String email;
    private String senha;
    private Role role;

}
