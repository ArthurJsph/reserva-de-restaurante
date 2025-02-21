package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.Entity.Usuario;
import com.myproject.reserva_restaurantes.repository.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class usuarioService {

    @Autowired
    private static usuarioRepository UsuarioRepository;

    public List<Usuario> getUsuarios() {
        try{
            return UsuarioRepository.findAll();
        }
        catch (DataAccessException e){
            System.err.print("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public static Usuario getByIdUsuarios(long id) {
        try{
            return UsuarioRepository.findById(id).get();
        }
        catch (DataAccessException e){
            System.err.print("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public Usuario saveUsuarios(Usuario usuario) {
        try{
            return UsuarioRepository.save(usuario);
        }
        catch (DataAccessException e){
            System.err.print("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public void deleteUsuarios(long id) {
        try{
            UsuarioRepository.deleteById(id);
        }
        catch (DataAccessException e){
            System.err.print("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }




}
