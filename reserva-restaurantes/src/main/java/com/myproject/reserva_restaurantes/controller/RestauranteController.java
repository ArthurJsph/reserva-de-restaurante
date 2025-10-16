package com.myproject.reserva_restaurantes.controller;

import com.myproject.reserva_restaurantes.dto.request.RestauranteRequest;
import com.myproject.reserva_restaurantes.dto.response.RestauranteResponse;
import com.myproject.reserva_restaurantes.service.RestauranteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    private static final Logger logger = LoggerFactory.getLogger(RestauranteController.class);

    private ResponseEntity<?> handleException(Exception e, String errorMessage) {
        logger.error(errorMessage, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage + ": " + e.getMessage());
    }

    @GetMapping("/listar")
    public ResponseEntity<?> obterTudo() {
        try {
            List<RestauranteResponse> restaurantes = restauranteService.getRestaurantes();
            if (restaurantes == null || restaurantes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(restaurantes);
        } catch (Exception e) {
            return handleException(e, "Erro ao buscar restaurantes");
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> obterPorId(@PathVariable Long id) {
        try {
            RestauranteResponse restaurante = restauranteService.getByIdRestaurantes(id);
            if (restaurante == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(restaurante);
        } catch (Exception e) {
            return handleException(e, "Erro ao buscar restaurante por ID");
        }
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody RestauranteRequest request) {
        try {
            if (request == null) {
                return ResponseEntity.badRequest().body("O corpo da requisição não pode ser nulo");
            }
            RestauranteResponse novoRestaurante = restauranteService.saveRestaurantes(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoRestaurante);
        } catch (Exception e) {
            return handleException(e, "Erro ao criar restaurante");
        }
    }

    @PutMapping("/atualizar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RestauranteRequest request) {
        try {
            if (request == null) {
                return ResponseEntity.badRequest().body("O corpo da requisição não pode ser nulo");
            }
            RestauranteResponse atualizado = restauranteService.updateRestaurante(id, request);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return handleException(e, "Erro ao atualizar restaurante");
        }
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            restauranteService.deleteRestaurantes(id);
            return ResponseEntity.ok("Restaurante deletado com sucesso");
        } catch (Exception e) {
            return handleException(e, "Erro ao deletar restaurante");
        }
    }
}
