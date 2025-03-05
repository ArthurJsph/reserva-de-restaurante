package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.Entity.Usuario;
import com.myproject.reserva_restaurantes.repository.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class usuarioService {

    @Autowired
    private usuarioRepository UsuarioRepository; // Não deve ser static

    public List<Usuario> getUsuarios() {
        try {
            return UsuarioRepository.findAll();
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public Usuario getByIdUsuarios(long id) { // Não deve ser static
        try {
            return UsuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        } catch (Exception e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public Usuario saveUsuarios(Usuario usuario) {
        try {
            return UsuarioRepository.save(usuario);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public void deleteUsuarios(long id) {
        try {
            UsuarioRepository.deleteById(id);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }

}
