package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.dto.mapper.UsuarioMapper;
import com.myproject.reserva_restaurantes.dto.request.UsuarioRequest;
import com.myproject.reserva_restaurantes.dto.response.UsuarioResponse;
import com.myproject.reserva_restaurantes.model.Usuario;
import com.myproject.reserva_restaurantes.model.Role;
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
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public List<UsuarioResponse> findAll() {
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            return usuarioMapper.toResponseList(usuarios);
        } catch (Exception e) {
            logger.error("Erro ao buscar todos os usuários", e);
            throw new RuntimeException("Erro ao recuperar usuários do banco de dados", e);
        }
    }

    public UsuarioResponse findById(Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
            return usuarioMapper.toResponse(usuario);
        } catch (Exception e) {
            logger.error("Erro ao buscar usuário por ID: {}", id, e);
            throw new RuntimeException("Erro ao recuperar usuário do banco de dados", e);
        }
    }

    public Optional<UsuarioResponse> findByEmail(String email) {
        try {
            return usuarioRepository.findByEmail(email)
                    .map(usuarioMapper::toResponse);
        } catch (Exception e) {
            logger.error("Erro ao buscar usuário por email: {}", email, e);
            throw new RuntimeException("Erro ao buscar usuário por email", e);
        }
    }

    public UsuarioResponse registerNewUser(UsuarioRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email já está em uso");
        }

        Usuario usuario = usuarioMapper.toEntity(request);
        usuario.setSenha(passwordEncoder.encode(request.getSenha().trim()));
        usuario.setRole(request.getRole());

        return save(usuario);
    }

    private UsuarioResponse save(Usuario usuario) {
        try {
            Usuario salvo = usuarioRepository.save(usuario);
            return usuarioMapper.toResponse(salvo);
        } catch (Exception e) {
            logger.error("Erro ao salvar usuário: {}", usuario.getEmail(), e);
            throw new RuntimeException("Erro ao persistir usuário no banco de dados", e);
        }
    }

    public UsuarioResponse update(Long id, UsuarioRequest request) {
        try {
            Usuario existente = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

            existente.setNome(request.getNome());
            existente.setEmail(request.getEmail());
            existente.setSenha(passwordEncoder.encode(request.getSenha().trim()));
            // O role não deve ser atualizado aqui, a menos que você tenha controle de permissões

            Usuario atualizado = usuarioRepository.save(existente);
            return usuarioMapper.toResponse(atualizado);
        } catch (Exception e) {
            logger.error("Erro ao atualizar usuário com ID: {}", id, e);
            throw new RuntimeException("Erro ao atualizar usuário no banco de dados", e);
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

