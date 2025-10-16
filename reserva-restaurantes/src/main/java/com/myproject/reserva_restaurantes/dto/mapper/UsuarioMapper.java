package com.myproject.reserva_restaurantes.dto.mapper;

import com.myproject.reserva_restaurantes.dto.request.RestauranteRequest;
import com.myproject.reserva_restaurantes.dto.request.UsuarioRequest;
import com.myproject.reserva_restaurantes.dto.response.RestauranteResponse;
import com.myproject.reserva_restaurantes.dto.response.UsuarioResponse;
import com.myproject.reserva_restaurantes.model.Restaurante;
import com.myproject.reserva_restaurantes.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioRequest request);
    UsuarioResponse toResponse(Usuario usuario);
    List<UsuarioResponse> toResponseList(List<Usuario> usuarios);
}

