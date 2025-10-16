package com.myproject.reserva_restaurantes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteRequest {
    private String nome;
    private String endereco;
    private String telefone;
    private int capacidade;
    private String horarioAbertura;
    private String horarioFechamento;
    private String imagemUrl;


}


