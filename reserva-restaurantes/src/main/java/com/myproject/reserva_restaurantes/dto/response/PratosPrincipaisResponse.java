package com.myproject.reserva_restaurantes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PratosPrincipaisResponse {
    private Long id;
    private String nome;
    private double avaliacao;
    private String imagemUrl;
}
