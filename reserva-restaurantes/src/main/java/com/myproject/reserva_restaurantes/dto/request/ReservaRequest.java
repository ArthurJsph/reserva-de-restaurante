package com.myproject.reserva_restaurantes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRequest {
    private LocalDate data;
    private LocalTime hora;
    private int numeroPessoas;
    private String nomeResponsavel;
    private String cpfResponsavel;
    private String telefoneResponsavel;
    private long idUsuario;
    private long idRestaurante;
}

