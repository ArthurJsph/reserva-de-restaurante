package com.myproject.reserva_restaurantes.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaResponse {

    private Long id;
    private String nomeCliente;
    private LocalDate dataReserva;
    private LocalTime horaReserva;
    private int numeroPessoas;
    private String restauranteNome;
    private long restauranteId;
}
