package com.myproject.reserva_restaurantes.repository;

import com.myproject.reserva_restaurantes.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByData(LocalDate data);



}
