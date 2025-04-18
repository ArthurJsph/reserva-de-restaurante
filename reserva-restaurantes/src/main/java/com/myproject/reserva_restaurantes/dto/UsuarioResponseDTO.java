package com.myproject.reserva_restaurantes.dto;

import com.myproject.reserva_restaurantes.Entity.Reserva;
import com.myproject.reserva_restaurantes.Entity.Role;
import lombok.*;

import java.util.List;


@Setter
@Getter
@Builder
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Role role;

    private List<Reserva> reservas;
}
