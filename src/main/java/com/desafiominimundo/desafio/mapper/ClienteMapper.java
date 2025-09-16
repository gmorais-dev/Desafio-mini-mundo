package com.desafiominimundo.desafio.mapper;


import com.desafiominimundo.desafio.dto.ClienteDto;
import com.desafiominimundo.desafio.entity.Cliente;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(ClienteDto dto);
    ClienteDto toDto(Cliente cliente);
}
