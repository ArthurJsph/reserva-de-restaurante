package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.Entity.Usuario;
import com.myproject.reserva_restaurantes.repository.usuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final usuarioRepository UsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UsuarioRepository.findByEmail(email)
                .map(this::buildUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }

    private UserDetails buildUserDetails(Usuario usuario) {
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .authorities(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name()))
                .build();
    }
}
