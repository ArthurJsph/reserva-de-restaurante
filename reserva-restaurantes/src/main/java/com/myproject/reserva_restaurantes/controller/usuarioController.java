package com.myproject.reserva_restaurantes.controller;

import com.myproject.reserva_restaurantes.Entity.Reserva;
import com.myproject.reserva_restaurantes.Entity.Usuario;
import com.myproject.reserva_restaurantes.service.reservaService;
import com.myproject.reserva_restaurantes.service.usuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuario")
public class usuarioController {

    @Autowired
    private usuarioService UsuarioService;

    @GetMapping()
    public ResponseEntity<?> getUsuarios() {
        try{
            List<Usuario> usuarios = UsuarioService.getUsuarios();
            return ResponseEntity.ok().body(usuarios);
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao buscar usuarios" + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        try{
            Optional<Usuario> usuarios = Optional.ofNullable(UsuarioService.getByIdUsuarios(id));
            if (usuarios.isPresent()) {
                return ResponseEntity.ok(usuarios.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        try{
            return ResponseEntity.ok().body("Usuario criado");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao criar usuario");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        try{
            UsuarioService.deleteUsuarios(id);
            return ResponseEntity.ok().body("Usuario deletado");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao deletar usuario");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body("Usuario atualizado");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao atualizar usuario");
        }
    }

}
