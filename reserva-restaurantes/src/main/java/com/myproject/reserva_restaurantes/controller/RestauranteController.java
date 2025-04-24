package com.myproject.reserva_restaurantes.controller;

import com.myproject.reserva_restaurantes.Entity.Restaurante;
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

    // Método auxiliar para tratar exceções
    private ResponseEntity<?> handleException(Exception e, String errorMessage) {
        logger.error(errorMessage, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage + ": " + e.getMessage());
    }

    @GetMapping
    public ResponseEntity<?> getRestaurantes() {
        try {
            List<Restaurante> restaurantes = restauranteService.getRestaurantes();
            if (restaurantes.isEmpty()) {
                return ResponseEntity.noContent().build(); // Retorna 204 se não houver restaurantes
            }
            return ResponseEntity.ok(restaurantes);
        } catch (Exception e) {
            return handleException(e, "Erro ao buscar restaurantes");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestauranteById(@PathVariable Long id) {
        try {
            Restaurante restaurante = restauranteService.getByIdRestaurantes(id);
            if (restaurante == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(restaurante);
        } catch (Exception e) {
            return handleException(e, "Erro ao buscar restaurante por ID");
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createRestaurante(@RequestBody Restaurante restaurante) {
        try {
            if (restaurante == null) {
                return ResponseEntity.badRequest().body("O corpo da requisição não pode ser nulo");
            }
            Restaurante novoRestaurante = restauranteService.saveRestaurantes(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoRestaurante);
        } catch (Exception e) {
            return handleException(e, "Erro ao criar restaurante");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteRestaurante(@PathVariable Long id) {
        try {
            Restaurante restaurante = restauranteService.getByIdRestaurantes(id);
            if (restaurante == null) {
                return ResponseEntity.notFound().build();
            }
            restauranteService.deleteRestaurantes(id);
            return ResponseEntity.ok("Restaurante deletado com sucesso");
        } catch (Exception e) {
            return handleException(e, "Erro ao deletar restaurante");
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateRestaurante(@PathVariable Long id, @RequestBody Restaurante restauranteAtualizado) {
        try {
            if (restauranteAtualizado == null) {
                return ResponseEntity.badRequest().body("O corpo da requisição não pode ser nulo");
            }
            Restaurante restauranteExistente = restauranteService.getByIdRestaurantes(id);
            if (restauranteExistente == null) {
                return ResponseEntity.notFound().build();
            }
            // Atualiza os campos do restaurante existente
            restauranteExistente.setNome(restauranteAtualizado.getNome());
            restauranteExistente.setEndereco(restauranteAtualizado.getEndereco());
            restauranteExistente.setCapacidade(restauranteAtualizado.getCapacidade());
            restauranteExistente.setHorarioAbertura(restauranteAtualizado.getHorarioAbertura());
            restauranteExistente.setHorarioFechamento(restauranteAtualizado.getHorarioFechamento());
            restauranteExistente.setImagem_url(restauranteAtualizado.getImagem_url());

            Restaurante restauranteAtualizadoNoBanco = restauranteService.saveRestaurantes(restauranteExistente);
            return ResponseEntity.ok(restauranteAtualizadoNoBanco);
        } catch (Exception e) {
            return handleException(e, "Erro ao atualizar restaurante");
        }
    }
}

