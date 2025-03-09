package com.myproject.reserva_restaurantes.controller;

import com.myproject.reserva_restaurantes.Entity.pratosPrincipais;
import com.myproject.reserva_restaurantes.service.pratosPrincipaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pratosPrincipais")
public class pratosPrincipaisController {

    @Autowired
    private pratosPrincipaisService PratosPrincipaisService;

    @GetMapping
    public ResponseEntity<?> getPratosPrincipais() {
        try {
            return ResponseEntity.ok(PratosPrincipaisService.getPratosPrincipais());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar pratos: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPratoPrincipalById(long id) {
        try {
            return ResponseEntity.ok(PratosPrincipaisService.getByIdPratosPrincipais(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar pratos pelo id: " + e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<?> create(pratosPrincipais pratosPrincipais) {
        try {
            return ResponseEntity.ok(PratosPrincipaisService.savePratosPrincipais(pratosPrincipais));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar pratos: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(long id) {
        try {
            return ResponseEntity.ok(PratosPrincipaisService.deletePratosPrincipais(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar pratos: " + e.getMessage());
        }
    }


}
