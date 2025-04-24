package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.Entity.Restaurante;
import com.myproject.reserva_restaurantes.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> getRestaurantes() {
        try {
            return restauranteRepository.findAll();
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public Restaurante getByIdRestaurantes(long id) {
        try {
            return restauranteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Restaurante não encontrado com ID: " + id));
        } catch (Exception e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public Restaurante saveRestaurantes(Restaurante restaurante) {
        try {
            return restauranteRepository.save(restaurante);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public void deleteRestaurantes(long id) {
        try {
            restauranteRepository.deleteById(id);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}
