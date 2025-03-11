package com.myproject.reserva_restaurantes.controller;

import com.myproject.reserva_restaurantes.Entity.Reserva;
import com.myproject.reserva_restaurantes.Entity.Usuario;
import com.myproject.reserva_restaurantes.service.reservaService;
import com.myproject.reserva_restaurantes.service.usuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/usuario")
public class usuarioController {

    @Autowired
    private usuarioService UsuarioService; // Usar camelCase para a variável

    @GetMapping
    public ResponseEntity<?> getUsuarios() {
        try {
            List<Usuario> usuarios = UsuarioService.getUsuarios();
            return ResponseEntity.ok().body(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar usuários: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        try {
            Usuario usuario = UsuarioService.getByIdUsuarios(id);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar usuário por ID: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@Validated @RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = UsuarioService.saveUsuarios(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        try {
            UsuarioService.deleteUsuarios(id);
            return ResponseEntity.ok("Usuário deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar usuário: " + e.getMessage());
        }
    }

}
