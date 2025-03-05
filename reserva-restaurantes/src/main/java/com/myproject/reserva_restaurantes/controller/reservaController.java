package com.myproject.reserva_restaurantes.controller;

import com.myproject.reserva_restaurantes.Entity.Reserva;
import com.myproject.reserva_restaurantes.service.reservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reserva")
public class reservaController {

    @Autowired
    private reservaService ReservaService;

    @GetMapping
    public ResponseEntity<?> getReservas() {
        try {
            List<Reserva> reservas = ReservaService.getReservas();
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar reservas: " + e.getMessage());
        }
    }

    @GetMapping("/data")
    public ResponseEntity<?> getReservasByData(@RequestParam LocalDate data) {
        try {
            List<Reserva> reservas = ReservaService.getReservasByData(data);
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar reservas por data: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservaById(@PathVariable Long id) {
        try {
            Optional<Reserva> reserva = ReservaService.getByIdReservas(id);
            if (reserva.isPresent()) {
                return ResponseEntity.ok(reserva.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar reserva por ID: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Reserva reserva) {
        try {
            Reserva novaReserva = ReservaService.saveReservas(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar reserva: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservas(@PathVariable Long id) {
        try {
            ReservaService.deleteReservas(id);
            return ResponseEntity.ok("Reserva deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar reserva: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservas(@PathVariable Long id, @RequestBody Reserva reservaAtualizada) {
        try {
            Optional<Reserva> reservaExistente = ReservaService.getByIdReservas(id);
            if (reservaExistente.isPresent()) {
                Reserva reserva = reservaExistente.get();
                reserva.setData(reservaAtualizada.getData());
                reserva.setHora(reservaAtualizada.getHora());
                reserva.setNumeroPessoas(reservaAtualizada.getNumeroPessoas());
                reserva.setNome_responsavel(reservaAtualizada.getNome_responsavel());
                reserva.setCpf_responsavel(reservaAtualizada.getCpf_responsavel());
                reserva.setTelefone_responsavel(reservaAtualizada.getTelefone_responsavel());
                reserva.setId(reservaAtualizada.getId());

                Reserva reservaAtualizadaNoBanco = ReservaService.saveReservas(reserva);
                return ResponseEntity.ok(reservaAtualizadaNoBanco);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar reserva: " + e.getMessage());
        }
    }
}
