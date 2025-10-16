package com.myproject.reserva_restaurantes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteResponse {
    private Long id;
    private String nome;
    private String endereco;
    private int capacidade;
    private String horarioAbertura;
    private String horarioFechamento;
    private String imagemUrl;
}

