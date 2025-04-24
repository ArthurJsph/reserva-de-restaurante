package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.Entity.Reserva;
import com.myproject.reserva_restaurantes.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository; // Não deve ser static

    public List<Reserva> getReservas() {
        try {
            return reservaRepository.findAll();
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public Optional<Reserva> getByIdReservas(long id) { // Não deve ser static
        try {
            return reservaRepository.findById(id);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Reserva saveReservas(Reserva reserva) {
        try {
            return reservaRepository.save(reserva);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public void deleteReservas(long id) {
        try {
            reservaRepository.deleteById(id);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }

    public List<Reserva> getReservasByData(LocalDate data) {
        try {
            return reservaRepository.findByData(data);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }
}
