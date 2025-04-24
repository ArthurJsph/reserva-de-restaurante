package com.myproject.reserva_restaurantes.Entity;


import jakarta.persistence.*;


@Entity
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
    private String imagem_url;

    public PratosPrincipais(long id, String nome, Double avaliacao, String imagem_url) {
        this.id = id;
        this.nome = nome;
        this.avaliacao = avaliacao;
        this.imagem_url = imagem_url;
    }

    public PratosPrincipais() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getImagem_url() {
        return imagem_url;
    }

    public void setImagem_url(String imagem_url) {
        this.imagem_url = imagem_url;
    }
}
