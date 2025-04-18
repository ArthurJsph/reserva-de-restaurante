package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.Entity.Usuario;
import com.myproject.reserva_restaurantes.Entity.Role;
import com.myproject.reserva_restaurantes.dto.LoginRequest;
import com.myproject.reserva_restaurantes.dto.RegisterRequest;
import com.myproject.reserva_restaurantes.dto.UsuarioResponseDTO;
import com.myproject.reserva_restaurantes.repository.usuarioRepository;
import com.myproject.reserva_restaurantes.security.AuthenticationResponse;
import com.myproject.reserva_restaurantes.security.JwtUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final usuarioRepository UsuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private UsuarioResponseDTO convertToDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .cpf(usuario.getCpf())
                .telefone(usuario.getTelefone())
                .role(Role.valueOf(usuario.getRole().name()))
                .reservas(usuario.getReservas())
                .build();
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        Usuario usuario = UsuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + request.getEmail()));

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        String token = jwtUtil.generateToken(usuario);
        UsuarioResponseDTO usuarioDTO = convertToDTO(usuario);
        return new AuthenticationResponse(token, usuarioDTO);
    }

    public Usuario register(RegisterRequest request) {
        if (UsuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já está em uso");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(request.getNome());
        novoUsuario.setCpf(request.getCpf());
        novoUsuario.setTelefone(request.getTelefone());
        novoUsuario.setEmail(request.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(request.getSenha().trim()));
        novoUsuario.setRole(Role.ROLE_USER);

        return UsuarioRepository.save(novoUsuario);
    }

}