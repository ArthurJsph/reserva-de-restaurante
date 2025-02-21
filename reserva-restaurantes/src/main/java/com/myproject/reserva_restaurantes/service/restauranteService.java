package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.Entity.Restaurante;
import com.myproject.reserva_restaurantes.repository.restauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class restauranteService {

    @Autowired
    private static restauranteRepository RestauranteRepository;

    public List<Restaurante> getRestaurantes() {
        try{
            return RestauranteRepository.findAll();
        }
        catch (DataAccessException e){
            System.err.print("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }

    }

    public Restaurante getByIdRestaurantes(long id) {
        try{
            return RestauranteRepository.findById(id).get();
        }
        catch (DataAccessException e){
            System.err.print("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public Restaurante saveRestaurantes(Restaurante restaurante) {
        try{
            return RestauranteRepository.save(restaurante);
        }
        catch (DataAccessException e){
            System.err.print("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public void deleteRestaurantes(long id) {
        try{
            RestauranteRepository.deleteById(id);
        }
        catch (DataAccessException e){
            System.err.print("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }




}
