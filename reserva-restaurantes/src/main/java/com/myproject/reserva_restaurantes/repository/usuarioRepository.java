package com.myproject.reserva_restaurantes.repository;

import com.myproject.reserva_restaurantes.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface usuarioRepository extends JpaRepository<Usuario, Long> {


}
