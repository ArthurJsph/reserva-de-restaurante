package com.myproject.reserva_restaurantes.repository;


import com.myproject.reserva_restaurantes.Entity.PratosPrincipais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PratosPrincipaisRepository extends JpaRepository<PratosPrincipais, Long> {

}
