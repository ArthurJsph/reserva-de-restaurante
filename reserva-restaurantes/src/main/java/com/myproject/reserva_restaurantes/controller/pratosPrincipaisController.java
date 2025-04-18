package com.myproject.reserva_restaurantes.controller;

import com.myproject.reserva_restaurantes.Entity.pratosPrincipais;
import com.myproject.reserva_restaurantes.service.pratosPrincipaisService;
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
public class pratosPrincipaisController {

    @Autowired
    private pratosPrincipaisService PratosPrincipaisService;

    private static final Logger logger = LoggerFactory.getLogger(pratosPrincipaisController.class);

    //Trata exceções de forma mais geral
    private ResponseEntity<?> handleException(Exception e, String errorMessage) {
        logger.error(errorMessage, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage + ": " + e.getMessage());
    }

    @GetMapping
    public ResponseEntity<?> getPratosPrincipais() {
        try {
            List<pratosPrincipais> pratos = PratosPrincipaisService.getPratosPrincipais();
            if (pratos.isEmpty()) {
                return ResponseEntity.noContent().build(); // Retorna 204 se não houver pratos
            }
            return ResponseEntity.ok(pratos);
        } catch (Exception e) {
            return handleException(e, "Erro ao buscar pratos principais");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPratoPrincipalById(@PathVariable Long id) {
        try {
            Optional<pratosPrincipais> prato = PratosPrincipaisService.getByIdPratosPrincipais(id);
            if (prato == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(prato);
        } catch (Exception e) {
            return handleException(e, "Erro ao buscar prato principal por ID");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody pratosPrincipais pratosPrincipais) {
        try {
            if (pratosPrincipais == null) {
                return ResponseEntity.badRequest().body("O corpo da requisição não pode ser nulo");
            }
            pratosPrincipais novoPrato = PratosPrincipaisService.savePratosPrincipais(pratosPrincipais);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPrato);
        } catch (Exception e) {
            return handleException(e, "Erro ao criar prato principal");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Optional<pratosPrincipais> prato = PratosPrincipaisService.getByIdPratosPrincipais(id);
            if (prato == null) {
                return ResponseEntity.notFound().build();
            }
            PratosPrincipaisService.deletePratosPrincipais(id);
            return ResponseEntity.ok("Prato principal deletado com sucesso");
        } catch (Exception e) {
            return handleException(e, "Erro ao deletar prato principal");
        }
    }
}
