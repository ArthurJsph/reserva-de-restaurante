package com.myproject.reserva_restaurantes.repository;

import com.myproject.reserva_restaurantes.Entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
