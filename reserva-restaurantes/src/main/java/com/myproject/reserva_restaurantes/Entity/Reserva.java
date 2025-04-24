package com.myproject.reserva_restaurantes.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
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
    private String nome_responsavel;
    @Column(name = "cpf_responsavel", nullable = false)
    private String cpf_responsavel;
    @Column(name = "telefone_responsavel", nullable = false)
    private String telefone_responsavel;


    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    @JsonBackReference
    private Restaurante restaurante;

    public Reserva() {
    }

    public Reserva(LocalDate data, LocalTime hora, int numeroPessoas, String nome_responsavel, String cpf_responsavel, String telefone_responsavel, Usuario usuario, Restaurante restaurante) {
        this.data = data;
        this.hora = hora;
        this.numeroPessoas = numeroPessoas;
        this.nome_responsavel = nome_responsavel;
        this.cpf_responsavel = cpf_responsavel;
        this.telefone_responsavel = telefone_responsavel;
        this.usuario = usuario;
        this.restaurante = restaurante;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getNumeroPessoas() {
        return numeroPessoas;
    }

    public void setNumeroPessoas(int numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }

    public String getNome_responsavel() {
        return nome_responsavel;
    }

    public void setNome_responsavel(String nome_responsavel) {
        this.nome_responsavel = nome_responsavel;
    }

    public String getCpf_responsavel() {
        return cpf_responsavel;
    }

    public void setCpf_responsavel(String cpf_responsavel) {
        this.cpf_responsavel = cpf_responsavel;
    }

    public String getTelefone_responsavel() {
        return telefone_responsavel;
    }

    public void setTelefone_responsavel(String telefone_responsavel) {
        this.telefone_responsavel = telefone_responsavel;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
