package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.Entity.pratosPrincipais;
import com.myproject.reserva_restaurantes.repository.pratosPrincipaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class pratosPrincipaisService {
    @Autowired
    private pratosPrincipaisRepository PratosPrincipaisRepository;


    public List<pratosPrincipais> getPratosPrincipais() {
        try {
            return PratosPrincipaisRepository.findAll();
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public Optional<pratosPrincipais> getByIdPratosPrincipais(long id) {
        try {
            return PratosPrincipaisRepository.findById(id);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return Optional.empty();
        }
    }

    public pratosPrincipais savePratosPrincipais(pratosPrincipais pratosPrincipais) {
        try {
            return PratosPrincipaisRepository.save(pratosPrincipais);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public pratosPrincipais deletePratosPrincipais(long id) {
        try {
            PratosPrincipaisRepository.deleteById(id);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
        return null;
    }
}
