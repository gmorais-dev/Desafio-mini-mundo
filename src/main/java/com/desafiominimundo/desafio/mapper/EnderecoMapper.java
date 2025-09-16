package com.desafiominimundo.desafio.mapper;

import com.desafiominimundo.desafio.dto.EnderecoDto;
import com.desafiominimundo.desafio.entity.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    Endereco toEntity(EnderecoDto dto);

    EnderecoDto toDto(Endereco endereco);


}
