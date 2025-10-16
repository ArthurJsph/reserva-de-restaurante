package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.dto.mapper.PratosPrincipaisMapper;
import com.myproject.reserva_restaurantes.dto.request.PratosPrincipaisRequest;
import com.myproject.reserva_restaurantes.dto.response.PratosPrincipaisResponse;
import com.myproject.reserva_restaurantes.model.PratosPrincipais;
import com.myproject.reserva_restaurantes.repository.PratosPrincipaisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PratosPrincipaisService {

    @Autowired
    private PratosPrincipaisRepository pratosRepository;

    @Autowired
    private PratosPrincipaisMapper pratosMapper;

    private static final Logger logger = LoggerFactory.getLogger(PratosPrincipaisService.class);

    public List<PratosPrincipaisResponse> findAll() {
        try {
            List<PratosPrincipais> pratos = pratosRepository.findAll();
            return pratosMapper.toResponseList(pratos);
        } catch (Exception e) {
            logger.error("Erro ao buscar todos os pratos principais", e);
            throw new RuntimeException("Erro ao recuperar pratos principais do banco de dados", e);
        }
    }

    public PratosPrincipaisResponse findById(Long id) {
        try {
            PratosPrincipais prato = pratosRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Prato principal não encontrado com ID: " + id));
            return pratosMapper.toResponse(prato);
        } catch (Exception e) {
            logger.error("Erro ao buscar prato principal por ID: {}", id, e);
            throw new RuntimeException("Erro ao recuperar prato principal do banco de dados", e);
        }
    }

    public PratosPrincipaisResponse save(PratosPrincipaisRequest request) {
        try {
            PratosPrincipais prato = pratosMapper.toEntity(request);
            PratosPrincipais salvo = pratosRepository.save(prato);
            return pratosMapper.toResponse(salvo);
        } catch (Exception e) {
            logger.error("Erro ao salvar prato principal", e);
            throw new RuntimeException("Erro ao salvar prato principal no banco de dados", e);
        }
    }

    public PratosPrincipaisResponse update(Long id, PratosPrincipaisRequest request) {
        try {
            PratosPrincipais existente = pratosRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Prato principal não encontrado com ID: " + id));

            existente.setNome(request.getNome());
            existente.setAvaliacao(request.getAvaliacao());
            existente.setImagemUrl(request.getImagemUrl());

            PratosPrincipais atualizado = pratosRepository.save(existente);
            return pratosMapper.toResponse(atualizado);
        } catch (Exception e) {
            logger.error("Erro ao atualizar prato principal com ID: {}", id, e);
            throw new RuntimeException("Erro ao atualizar prato principal no banco de dados", e);
        }
    }

    public void delete(Long id) {
        try {
            if (!pratosRepository.existsById(id)) {
                throw new RuntimeException("Prato principal não encontrado com ID: " + id);
            }
            pratosRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erro ao deletar prato principal com ID: {}", id, e);
            throw new RuntimeException("Erro ao deletar prato principal do banco de dados", e);
        }
    }
}

