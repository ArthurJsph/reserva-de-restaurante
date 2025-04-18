package com.myproject.reserva_restaurantes.controller;

import com.myproject.reserva_restaurantes.Entity.Usuario;
import com.myproject.reserva_restaurantes.dto.UsuarioRequestDTO;
import com.myproject.reserva_restaurantes.dto.UsuarioResponseDTO;
import com.myproject.reserva_restaurantes.service.usuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class usuarioController {

    private final usuarioService UsuarioService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(UsuarioService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(convertToDTO(UsuarioService.findById(id)));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = convertToEntity(usuarioDTO);
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDTO(UsuarioService.save(usuario)));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO usuarioDTO) {

        Usuario usuarioExistente = UsuarioService.findById(id);
        usuarioExistente.setNome(usuarioDTO.getNome());
        usuarioExistente.setCpf(usuarioDTO.getCpf());
        usuarioExistente.setTelefone(usuarioDTO.getTelefone());
        usuarioExistente.setEmail(usuarioDTO.getEmail());

        if (usuarioDTO.getSenha() != null && !usuarioDTO.getSenha().trim().isEmpty()) {
            usuarioExistente.setSenha(passwordEncoder.encode(usuarioDTO.getSenha().trim()));
        }
        Usuario usuarioAtualizado = UsuarioService.save(usuarioExistente);
        return ResponseEntity.ok(convertToDTO(usuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        UsuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private UsuarioResponseDTO convertToDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .role(usuario.getRole())
                .build();
    }

    private Usuario convertToEntity(UsuarioRequestDTO dto) {
        return Usuario.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha().trim()))
                .role(dto.getRole())
                .build();
    }
}
