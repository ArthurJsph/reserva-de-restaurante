package com.myproject.reserva_restaurantes.repository;


import com.myproject.reserva_restaurantes.Entity.pratosPrincipais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface pratosPrincipaisRepository extends JpaRepository<pratosPrincipais, Long> {

}
