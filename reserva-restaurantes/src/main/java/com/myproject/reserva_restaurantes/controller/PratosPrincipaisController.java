package com.myproject.reserva_restaurantes.controller;

import com.myproject.reserva_restaurantes.dto.request.PratosPrincipaisRequest;
import com.myproject.reserva_restaurantes.model.PratosPrincipais;
import com.myproject.reserva_restaurantes.service.PratosPrincipaisService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/pratosPrincipais")
public class PratosPrincipaisController {

    @Autowired
    private PratosPrincipaisService pratosPrincipaisService;

    private static final Logger logger = LoggerFactory.getLogger(PratosPrincipaisController.class);

    private ResponseEntity<?> handleException(Exception e, String errorMessage) {
        logger.error(errorMessage, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage + ": " + e.getMessage());
    }

    @GetMapping("/listar")
    public ResponseEntity<?> obterTudo() {
        try {
            return ResponseEntity.ok(pratosPrincipaisService.findAll());
        } catch (Exception e) {
            return handleException(e, "Erro ao buscar pratos principais");
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> obterPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pratosPrincipaisService.findById(id));
        } catch (Exception e) {
            return handleException(e, "Erro ao buscar prato principal por ID");
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> create(@Valid @RequestBody PratosPrincipaisRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pratosPrincipaisService.save(request));
        } catch (Exception e) {
            return handleException(e, "Erro ao criar prato principal");
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PratosPrincipaisRequest request) {
        try {
            return ResponseEntity.ok(pratosPrincipaisService.update(id, request));
        } catch (Exception e) {
            return handleException(e, "Erro ao atualizar prato principal");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            pratosPrincipaisService.delete(id);
            return ResponseEntity.ok("Prato principal deletado com sucesso");
        } catch (Exception e) {
            return handleException(e, "Erro ao deletar prato principal");
        }
    }
}

