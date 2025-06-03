package com.myproject.reserva_restaurantes.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pratos_principais")
public class PratosPrincipais {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    @Column(name="nome",nullable = false)
    private String nome;
    @Column(name="avaliacao",nullable = false)
    private Double avaliacao;
    @Column(name="imagem_url",nullable = false)
    private String imagemUrl;

}
