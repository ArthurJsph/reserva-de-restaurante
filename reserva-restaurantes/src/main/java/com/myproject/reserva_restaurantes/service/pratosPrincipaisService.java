package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.Entity.PratosPrincipais;
import com.myproject.reserva_restaurantes.repository.PratosPrincipaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PratosPrincipaisService {
    @Autowired
    private PratosPrincipaisRepository pratosPrincipaisRepository;


    public List<PratosPrincipais> getPratosPrincipais() {
        try {
            return pratosPrincipaisRepository.findAll();
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public Optional<PratosPrincipais> getByIdPratosPrincipais(long id) {
        try {
            return pratosPrincipaisRepository.findById(id);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return Optional.empty();
        }
    }

    public PratosPrincipais savePratosPrincipais(PratosPrincipais pratosPrincipais) {
        try {
            return pratosPrincipaisRepository.save(pratosPrincipais);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return null;
        }
    }

    public PratosPrincipais deletePratosPrincipais(long id) {
        try {
            pratosPrincipaisRepository.deleteById(id);
        } catch (DataAccessException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
        return null;
    }
}
