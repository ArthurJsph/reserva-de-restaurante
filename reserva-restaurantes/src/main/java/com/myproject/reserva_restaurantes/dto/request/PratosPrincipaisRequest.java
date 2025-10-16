package com.myproject.reserva_restaurantes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PratosPrincipaisRequest {
    private String nome;
    private double avaliacao;
    private String imagemUrl;

}
