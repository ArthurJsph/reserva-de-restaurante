package com.myproject.reserva_restaurantes.service;

import com.myproject.reserva_restaurantes.dto.mapper.ReservaMapper;
import com.myproject.reserva_restaurantes.dto.request.ReservaRequest;
import com.myproject.reserva_restaurantes.dto.response.ReservaResponse;
import com.myproject.reserva_restaurantes.model.Reserva;
import com.myproject.reserva_restaurantes.model.Restaurante;
import com.myproject.reserva_restaurantes.repository.ReservaRepository;
import com.myproject.reserva_restaurantes.repository.RestauranteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;


    @Autowired
    private ReservaMapper reservaMapper;

    private static final Logger logger = LoggerFactory.getLogger(ReservaService.class);

    public List<ReservaResponse> findAll() {
        try {
            List<Reserva> reservas = reservaRepository.findAll();
            return reservaMapper.toResponseList(reservas);
        } catch (Exception e) {
            logger.error("Erro ao buscar todas as reservas", e);
            throw new RuntimeException("Erro ao recuperar reservas do banco de dados", e);
        }
    }

    public ReservaResponse findById(Long id) {
        try {
            Reserva reserva = reservaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Reserva não encontrada com ID: " + id));
            return reservaMapper.toResponse(reserva);
        } catch (Exception e) {
            logger.error("Erro ao buscar reserva por ID: {}", id, e);
            throw new RuntimeException("Erro ao recuperar reserva do banco de dados", e);
        }
    }


    public ReservaResponse save(ReservaRequest request) {
        try {
            Reserva reserva = reservaMapper.toEntity(request);
            Reserva salva = reservaRepository.save(reserva);
            return reservaMapper.toResponse(salva);
        } catch (Exception e) {
            logger.error("Erro ao salvar reserva", e);
            throw new RuntimeException("Erro ao salvar reserva no banco de dados", e);
        }
    }

    public ReservaResponse update(Long id, ReservaRequest request) {
        try {
            Reserva existente = reservaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Reserva não encontrada com ID: " + id));

            Restaurante restaurante = restauranteRepository.findById(request.getIdRestaurante())
                    .orElseThrow(() -> new RuntimeException("Restaurante não encontrado com ID: " + request.getIdRestaurante()));

            existente.setData(request.getData());
            existente.setHora(request.getHora());
            existente.setNumeroPessoas(request.getNumeroPessoas());
            existente.setNomeResponsavel(request.getNomeResponsavel());
            existente.setTelefoneResponsavel(request.getTelefoneResponsavel());
            existente.setRestaurante(restaurante); // Aqui passa o objeto Restaurante

            Reserva atualizada = reservaRepository.save(existente);
            return reservaMapper.toResponse(atualizada);
        } catch (Exception e) {
            logger.error("Erro ao atualizar reserva com ID: {}", id, e);
            throw new RuntimeException("Erro ao atualizar reserva no banco de dados", e);
        }
    }


    public void delete(Long id) {
        try {
            if (!reservaRepository.existsById(id)) {
                throw new RuntimeException("Reserva não encontrada com ID: " + id);
            }
            reservaRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erro ao deletar reserva com ID: {}", id, e);
            throw new RuntimeException("Erro ao deletar reserva do banco de dados", e);
        }
    }
}

