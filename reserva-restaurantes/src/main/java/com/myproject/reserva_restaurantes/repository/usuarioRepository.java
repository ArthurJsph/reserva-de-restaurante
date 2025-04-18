package com.myproject.reserva_restaurantes.repository;

import com.myproject.reserva_restaurantes.Entity.Reserva;
import com.myproject.reserva_restaurantes.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface usuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

    @Query("SELECT u FROM Usuario u JOIN FETCH u.reservas WHERE u.id = :id")
    Optional<Usuario> findByIdWithReservas(@Param("id") Long id);
}
