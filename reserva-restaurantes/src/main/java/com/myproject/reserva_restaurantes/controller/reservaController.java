package com.myproject.reserva_restaurantes.controller;

import com.myproject.reserva_restaurantes.Entity.Reserva;
import com.myproject.reserva_restaurantes.service.reservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reserva")
public class reservaController {

    @Autowired
    private reservaService ReservaService;

    @GetMapping()
    public  ResponseEntity<?> getReservas() {
        try{
            List<Reserva> reservas = ReservaService.getReservas();
            return ResponseEntity.ok(reservas);
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao buscar reservas" + e.getMessage());
        }

    }

    public ResponseEntity<?> getReservasByData(@RequestParam LocalDate data) {
        try{
            List<Reserva> reservas = ReservaService.getReservasByData(data);
            return ResponseEntity.ok(reservas);
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao buscar reservas");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        try {
            Optional<Reserva> reserva = reservaService.getByIdReservas(id);
            if (reserva.isPresent()) {
                return ResponseEntity.ok(reserva.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Reserva reserva) {
        try{
            return ResponseEntity.ok("Reserva criada com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao criar reserva");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservas(long id) {
        try{
            return ResponseEntity.ok("Reserva deletada com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao deletar reserva");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservas(long id) {
        try{
            return ResponseEntity.ok("Reserva atualizada com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao atualizar reserva");
        }
    }







}
