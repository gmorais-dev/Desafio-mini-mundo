package com.desafiominimundo.desafio.mapper;

import com.desafiominimundo.desafio.dto.MercadoriaDto;
import com.desafiominimundo.desafio.entity.Mercadoria;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MercadoriaMapper {
    Mercadoria toEntity(MercadoriaDto dto);
    MercadoriaDto toDto(Mercadoria mercadoria);
}
