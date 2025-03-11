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


}
