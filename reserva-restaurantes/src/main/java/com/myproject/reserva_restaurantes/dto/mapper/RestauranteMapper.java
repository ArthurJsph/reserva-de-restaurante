package com.myproject.reserva_restaurantes.dto.mapper;

import com.myproject.reserva_restaurantes.dto.request.RestauranteRequest;
import com.myproject.reserva_restaurantes.dto.response.RestauranteResponse;
import com.myproject.reserva_restaurantes.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {
    Restaurante toEntity(RestauranteRequest request);
    RestauranteResponse toResponse(Restaurante restaurante);
    List<RestauranteResponse> toResponseList(List<Restaurante> restaurantes);
}

