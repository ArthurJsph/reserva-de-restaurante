package com.myproject.reserva_restaurantes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private long id;
    @Column(name = "data_reserva", nullable = false)
    private LocalDate data;
    @Column(name = "hora_reserva", nullable = false)
    private LocalTime hora;
    @Column(name = "numero_pessoas", nullable = false)
    private int numeroPessoas;
    @Column(name = "nome_responsavel", nullable = false)
    private String nomeResponsavel;
    @Column(name = "cpf_responsavel", nullable = false)
    private String cpfResponsavel;
    @Column(name = "telefone_responsavel", nullable = false)
    private String telefoneResponsavel;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    @JsonBackReference
    private Restaurante restaurante;

}
