package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.dto.mapper.RestauranteMapper;
import com.myproject.reserva_restaurantes.model.Restaurante;
import com.myproject.reserva_restaurantes.repository.RestauranteRepository;
import com.myproject.reserva_restaurantes.dto.request.RestauranteRequest;
import com.myproject.reserva_restaurantes.dto.response.RestauranteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteMapper restauranteMapper;

    private static final Logger logger = LoggerFactory.getLogger(RestauranteService.class);

    public List<RestauranteResponse> getRestaurantes() {
        try {
            List<Restaurante> restaurantes = restauranteRepository.findAll();
            return restauranteMapper.toResponseList(restaurantes);
        } catch (DataAccessException e) {
            logger.error("Erro ao buscar todos os restaurantes", e);
            throw new RuntimeException("Erro ao recuperar restaurantes do banco de dados", e);
        }
    }

    public RestauranteResponse getByIdRestaurantes(long id) {
        try {
            Restaurante restaurante = restauranteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Restaurante não encontrado com ID: " + id));
            return restauranteMapper.toResponse(restaurante);
        } catch (Exception e) {
            logger.error("Erro ao buscar restaurante com ID: {}", id, e);
            throw new RuntimeException("Erro ao recuperar restaurante do banco de dados", e);
        }
    }

    public RestauranteResponse saveRestaurantes(RestauranteRequest request) {
        try {
            Restaurante restaurante = restauranteMapper.toEntity(request);
            Restaurante salvo = restauranteRepository.save(restaurante);
            return restauranteMapper.toResponse(salvo);
        } catch (DataAccessException e) {
            logger.error("Erro ao salvar restaurante", e);
            throw new RuntimeException("Erro ao salvar restaurante no banco de dados", e);
        }
    }

    public RestauranteResponse updateRestaurante(long id, RestauranteRequest request) {
        try {
            Restaurante existente = restauranteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Restaurante não encontrado com ID: " + id));

            existente.setNome(request.getNome());
            existente.setEndereco(request.getEndereco());
            existente.setCapacidade(request.getCapacidade());
            existente.setHorarioAbertura(LocalTime.parse(request.getHorarioAbertura()));
            existente.setHorarioFechamento(LocalTime.parse(request.getHorarioFechamento()));
            existente.setImagemUrl(request.getImagemUrl());

            Restaurante atualizado = restauranteRepository.save(existente);
            return restauranteMapper.toResponse(atualizado);
        } catch (DataAccessException e) {
            logger.error("Erro ao atualizar restaurante com ID: {}", id, e);
            throw new RuntimeException("Erro ao atualizar restaurante no banco de dados", e);
        }
    }

    public void deleteRestaurantes(long id) {
        try {
            if (!restauranteRepository.existsById(id)) {
                throw new RuntimeException("Restaurante não encontrado com ID: " + id);
            }
            restauranteRepository.deleteById(id);
        } catch (DataAccessException e) {
            logger.error("Erro ao deletar restaurante com ID: {}", id, e);
            throw new RuntimeException("Erro ao deletar restaurante do banco de dados", e);
        }
    }
}

