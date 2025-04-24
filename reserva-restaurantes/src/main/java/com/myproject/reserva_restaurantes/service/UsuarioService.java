package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.Entity.Usuario;
import com.myproject.reserva_restaurantes.Entity.Role;
import com.myproject.reserva_restaurantes.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public List<Usuario> findAll() {
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            logger.error("Erro ao buscar todos os usuários", e);
            throw new RuntimeException("Erro ao recuperar usuários do banco de dados", e);
        }
    }

    public Usuario findById(Long id) {
        try {
            return usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        } catch (Exception e) {
            logger.error("Erro ao buscar usuário por ID: {}", id, e);
            throw new RuntimeException("Erro ao recuperar usuário do banco de dados", e);
        }
    }

    public Optional<Usuario> findByEmail(String email) {
        try {
            return usuarioRepository.findByEmail(email);
        } catch (Exception e) {
            logger.error("Erro ao buscar usuário por email: {}", email, e);
            throw new RuntimeException("Erro ao buscar usuário por email", e);
        }
    }

    public Usuario registerNewUser(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já está em uso");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha().trim()));
        usuario.setRole(Role.ROLE_USER);

        return save(usuario);
    }

    public Usuario save(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            logger.error("Erro ao salvar usuário: {}", usuario.getEmail(), e);
            throw new RuntimeException("Erro ao persistir usuário no banco de dados", e);
        }
    }

    public void delete(Long id) {
        try {
            if (!usuarioRepository.existsById(id)) {
                throw new RuntimeException("Usuário não encontrado com ID: " + id);
            }
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erro ao deletar usuário com ID: {}", id, e);
            throw new RuntimeException("Erro ao deletar usuário do banco de dados", e);
        }
    }


}
