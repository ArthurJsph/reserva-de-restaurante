package com.myproject.reserva_restaurantes.controller;

import com.myproject.reserva_restaurantes.Entity.Reserva;
import com.myproject.reserva_restaurantes.Entity.Restaurante;
import com.myproject.reserva_restaurantes.Entity.Usuario;
import com.myproject.reserva_restaurantes.service.restauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/restaurante")
@CrossOrigin(origins = "http://localhost:4200")
public class restauranteController {

    @Autowired
    private restauranteService RestauranteService;

    @GetMapping
    public ResponseEntity<?> getRestaurantes() {
        try {
            List<Restaurante> restaurantes = RestauranteService.getRestaurantes();
            return ResponseEntity.ok(restaurantes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar restaurantes: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestauranteById(@PathVariable Long id) {
        try {
            Restaurante restaurante = RestauranteService.getByIdRestaurantes(id);
            if (restaurante != null) {
                return ResponseEntity.ok(restaurante);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar restaurante por ID: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createRestaurante(@RequestBody Restaurante restaurante) {
        try {
            Restaurante novoRestaurante = RestauranteService.saveRestaurantes(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoRestaurante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar restaurante: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurante(@PathVariable Long id) {
        try {
            RestauranteService.deleteRestaurantes(id);
            return ResponseEntity.ok("Restaurante deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar restaurante: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurante(@PathVariable Long id, @RequestBody Restaurante restauranteAtualizado) {
        try {
            Restaurante restauranteExistente = RestauranteService.getByIdRestaurantes(id);
            if (restauranteExistente != null) {
                restauranteExistente.setNome(restauranteAtualizado.getNome());
                restauranteExistente.setEndereco(restauranteAtualizado.getEndereco());
                restauranteExistente.setCapacidade(restauranteAtualizado.getCapacidade());
                restauranteExistente.setHorarioAbertura(restauranteAtualizado.getHorarioAbertura());
                restauranteExistente.setHorarioFechamento(restauranteAtualizado.getHorarioFechamento());
                restauranteExistente.setImagem_url(restauranteAtualizado.getImagem_url());

                Restaurante restauranteAtualizadoNoBanco = RestauranteService.saveRestaurantes(restauranteExistente);
                return ResponseEntity.ok(restauranteAtualizadoNoBanco);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar restaurante: " + e.getMessage());
        }
    }
}
