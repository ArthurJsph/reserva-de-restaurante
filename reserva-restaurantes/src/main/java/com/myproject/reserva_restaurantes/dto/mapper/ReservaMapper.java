package com.myproject.reserva_restaurantes.dto.mapper;

import com.myproject.reserva_restaurantes.dto.request.ReservaRequest;
import com.myproject.reserva_restaurantes.dto.response.ReservaResponse;
import com.myproject.reserva_restaurantes.model.Reserva;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {
    Reserva toEntity(ReservaRequest request);
    ReservaResponse toResponse(Reserva reserva);
    List<ReservaResponse> toResponseList(List<Reserva> reservas);
}

