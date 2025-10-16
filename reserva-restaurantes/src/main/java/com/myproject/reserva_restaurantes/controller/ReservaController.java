package com.myproject.reserva_restaurantes.controller;

import com.myproject.reserva_restaurantes.dto.request.ReservaRequest;
import com.myproject.reserva_restaurantes.model.Reserva;
import com.myproject.reserva_restaurantes.service.ReservaService;
import jakarta.validation.Valid;
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

    private ResponseEntity<?> handleException(Exception e, String errorMessage) {
        logger.error(errorMessage, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage + ": " + e.getMessage());
    }

    @GetMapping("/listar")
    public ResponseEntity<?> obterTudo() {
        try {
            return ResponseEntity.ok(reservaService.findAll());
        } catch (Exception e) {
            return handleException(e, "Erro ao buscar reservas");
        }
    }


    @GetMapping("/listar/{id}")
    public ResponseEntity<?> obterPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reservaService.findById(id));
        } catch (Exception e) {
            return handleException(e, "Erro ao buscar reserva por ID");
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> create(@Valid @RequestBody ReservaRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.save(request));
        } catch (Exception e) {
            return handleException(e, "Erro ao criar reserva");
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ReservaRequest request) {
        try {
            return ResponseEntity.ok(reservaService.update(id, request));
        } catch (Exception e) {
            return handleException(e, "Erro ao atualizar reserva");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            reservaService.delete(id);
            return ResponseEntity.ok("Reserva deletada com sucesso");
        } catch (Exception e) {
            return handleException(e, "Erro ao deletar reserva");
        }
    }
}

