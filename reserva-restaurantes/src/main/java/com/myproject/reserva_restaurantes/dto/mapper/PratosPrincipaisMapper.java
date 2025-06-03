package com.myproject.reserva_restaurantes.dto.mapper;

import com.myproject.reserva_restaurantes.dto.request.PratosPrincipaisRequest;
import com.myproject.reserva_restaurantes.dto.response.PratosPrincipaisResponse;
import com.myproject.reserva_restaurantes.model.PratosPrincipais;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PratosPrincipaisMapper {
    PratosPrincipais toEntity(PratosPrincipaisRequest request);
    PratosPrincipaisResponse toResponse(PratosPrincipais pratosPrincipais);
    List<PratosPrincipaisResponse> toResponseList(List<PratosPrincipais> pratosPrincipaisList);
}

