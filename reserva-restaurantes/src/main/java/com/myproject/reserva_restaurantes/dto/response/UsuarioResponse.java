package com.myproject.reserva_restaurantes.dto.response;

import com.myproject.reserva_restaurantes.model.Reserva;
import com.myproject.reserva_restaurantes.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Role role;
    private List<Reserva> reservas;
}