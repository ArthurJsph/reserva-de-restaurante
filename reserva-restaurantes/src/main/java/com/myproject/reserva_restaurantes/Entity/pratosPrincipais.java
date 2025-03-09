package com.myproject.reserva_restaurantes.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pratos_principais")
@Getter
@Setter
public class pratosPrincipais {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    @Column(name="nome",nullable = false)
    private String nome;
    @Column(name="avaliacao",nullable = false)
    private String avaliacao;
    @Column(name="imagem_url",nullable = false)
    private String imagem_url;

    public pratosPrincipais(long id, String nome, String avaliacao, String imagem_url) {
        this.id = id;
        this.nome = nome;
        this.avaliacao = avaliacao;
        this.imagem_url = imagem_url;
    }

    public pratosPrincipais() {
    }
}
