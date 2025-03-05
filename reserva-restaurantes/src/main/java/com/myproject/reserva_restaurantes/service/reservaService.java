package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.Entity.Reserva;
import com.myproject.reserva_restaurantes.repository.reservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class reservaService {

    @Autowired
    private reservaRepository ReservaRepository; // Não deve ser static

    public List<Reserva> getReservas() {
        try {
            return ReservaRepository.findAll();
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public Optional<Reserva> getByIdReservas(long id) { // Não deve ser static
        try {
            return ReservaRepository.findById(id);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Reserva saveReservas(Reserva reserva) {
        try {
            return ReservaRepository.save(reserva);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public void deleteReservas(long id) {
        try {
            ReservaRepository.deleteById(id);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }

    public List<Reserva> getReservasByData(LocalDate data) {
        try {
            return ReservaRepository.findByData(data);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }
}
