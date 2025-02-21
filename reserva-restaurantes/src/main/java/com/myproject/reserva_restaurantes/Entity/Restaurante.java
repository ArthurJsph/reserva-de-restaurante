package com.myproject.reserva_restaurantes.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurante")
@Getter
@Setter
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurante")
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private int capacidade;
    @Column(nullable = false)
    private LocalTime horarioAbertura;
    @Column(nullable = false)
    private LocalTime horarioFechamento;


    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas;

    public Restaurante(String nome, String endereco, int capacidade, LocalTime horarioAbertura, LocalTime horarioFechamento) {
        this.nome = nome;
        this.endereco = endereco;
        this.capacidade = capacidade;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
    }

    public Restaurante() {
    }


}
