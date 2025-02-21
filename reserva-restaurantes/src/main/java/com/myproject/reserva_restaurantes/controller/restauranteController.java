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
import java.util.Optional;

@RestController
@RequestMapping("/restaurante")
public class restauranteController {

    @Autowired
    private restauranteService RestauranteService;

    @GetMapping()
    public ResponseEntity<?> getRestaurantes() {
        try{
            List<Restaurante> restaurantes = RestauranteService.getRestaurantes();
            return ResponseEntity.ok(restaurantes);
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao buscar restaurantes");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> getRestauranteById(@PathVariable Long id) {
        try{
            Optional<Restaurante> restaurantes = Optional.ofNullable(RestauranteService.getByIdRestaurantes(id));

            if(restaurantes.isPresent()){
                return ResponseEntity.ok(restaurantes.get());
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> createRestaurante(@RequestBody Restaurante restaurante) {
        try{
            return ResponseEntity.ok("Restaurante criado");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao criar restaurante" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurante(Long id) {
        try{
            return ResponseEntity.ok("Restaurante deletado");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao deletar restaurante");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurante(Long id) {
        try{
            return ResponseEntity.ok("Restaurante atualizado");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao atualizar restaurante");
        }
    }
}
