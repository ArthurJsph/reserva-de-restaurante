package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.Entity.Usuario;
import com.myproject.reserva_restaurantes.repository.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class authService {
    @Autowired
    private usuarioRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String nome, String rawPassword) {
        Usuario user = userRepository.findByNome(nome)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Comparar a senha em texto plano com o hash do BD
        return passwordEncoder.matches(rawPassword, user.getSenha());
    }
}
