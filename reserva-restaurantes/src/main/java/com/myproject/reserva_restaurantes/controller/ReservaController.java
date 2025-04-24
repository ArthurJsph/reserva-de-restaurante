package com.myproject.reserva_restaurantes.controller;

import com.myproject.reserva_restaurantes.Entity.Reserva;
import com.myproject.reserva_restaurantes.service.ReservaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);

    // Método auxiliar para tratar exceções
    private ResponseEntity<?> handleException(Exception e, String errorMessage) {
        logger.error(errorMessage, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage + ": " + e.getMessage());
    }

    @GetMapping
    public ResponseEntity<?> getReservas() {
        try {
            List<Reserva> reservas = reservaService.getReservas();
            if (reservas.isEmpty()) {
                return ResponseEntity.noContent().build(); // Retorna 204 se não houver reservas
            }
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return handleException(e, "Erro ao buscar reservas");
        }
    }

    @GetMapping("/data")
    public ResponseEntity<?> getReservasByData(@RequestParam LocalDate data) {
        try {
            List<Reserva> reservas = reservaService.getReservasByData(data);
            if (reservas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return handleException(e, "Erro ao buscar reservas por data");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservaById(@PathVariable Long id) {
        try {
            Optional<Reserva> reserva = reservaService.getByIdReservas(id);
            if (reserva.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(reserva.get());
        } catch (Exception e) {
            return handleException(e, "Erro ao buscar reserva por ID");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Reserva reserva) {
        try {
            if (reserva == null) {
                return ResponseEntity.badRequest().body("O corpo da requisição não pode ser nulo");
            }
            Reserva novaReserva = reservaService.saveReservas(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
        } catch (Exception e) {
            return handleException(e, "Erro ao criar reserva");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservas(@PathVariable Long id) {
        try {
            Optional<Reserva> reserva = reservaService.getByIdReservas(id);
            if (reserva.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            reservaService.deleteReservas(id);
            return ResponseEntity.ok("Reserva deletada com sucesso");
        } catch (Exception e) {
            return handleException(e, "Erro ao deletar reserva");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservas(@PathVariable Long id, @RequestBody Reserva reservaAtualizada) {
        try {
            Optional<Reserva> reservaExistente = reservaService.getByIdReservas(id);
            if (reservaExistente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Reserva reserva = reservaExistente.get();
            reserva.setData(reservaAtualizada.getData());
            reserva.setHora(reservaAtualizada.getHora());
            reserva.setNumeroPessoas(reservaAtualizada.getNumeroPessoas());
            reserva.setNome_responsavel(reservaAtualizada.getNome_responsavel());
            reserva.setCpf_responsavel(reservaAtualizada.getCpf_responsavel());
            reserva.setTelefone_responsavel(reservaAtualizada.getTelefone_responsavel());

            Reserva reservaAtualizadaNoBanco = reservaService.saveReservas(reserva);
            return ResponseEntity.ok(reservaAtualizadaNoBanco);
        } catch (Exception e) {
            return handleException(e, "Erro ao atualizar reserva");
        }
    }
}
