package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.dto.request.UsuarioRequest;
import com.myproject.reserva_restaurantes.model.Usuario;
import com.myproject.reserva_restaurantes.model.Role;
import com.myproject.reserva_restaurantes.dto.request.LoginRequest;
import com.myproject.reserva_restaurantes.dto.response.UsuarioResponse;
import com.myproject.reserva_restaurantes.repository.UsuarioRepository;
import com.myproject.reserva_restaurantes.security.AuthenticationResponse;
import com.myproject.reserva_restaurantes.security.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    private UsuarioResponse convertToDTO(Usuario usuario) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setCpf(usuario.getCpf());
        dto.setTelefone(usuario.getTelefone());
        dto.setRole(Role.valueOf(usuario.getRole().name()));
        dto.setReservas(usuario.getReservas());
        return dto;
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + request.getEmail()));

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        String token = jwtUtil.generateToken((UserDetails) usuario);
        UsuarioResponse usuarioDTO = convertToDTO(usuario);
        return new AuthenticationResponse(token, usuarioDTO);
    }

    public Usuario register(UsuarioRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já está em uso");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(request.getNome());
        novoUsuario.setCpf(request.getCpf());
        novoUsuario.setTelefone(request.getTelefone());
        novoUsuario.setEmail(request.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(request.getSenha().trim()));
        novoUsuario.setRole(request.getRole());
        ;

        return usuarioRepository.save(novoUsuario);
    }
}