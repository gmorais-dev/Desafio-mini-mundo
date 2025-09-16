package com.desafiominimundo.desafio.mapper;

import com.desafiominimundo.desafio.dto.EntregaDto;
import com.desafiominimundo.desafio.entity.Entrega;
import com.desafiominimundo.desafio.entity.Mercadoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EntregaMapper {

    @Mapping(target = "remetente", ignore = true)
    @Mapping(target = "destinatario", ignore = true)
    @Mapping(target = "mercadorias", ignore = true)
    @Mapping(target = "status", source = "statusEntrega")
    Entrega toEntity(EntregaDto dto);

    @Mapping(source = "remetente.id", target = "remetenteId")
    @Mapping(source = "destinatario.id", target = "destinatarioId")
    @Mapping(source = "mercadorias", target = "mercadoriaIds")
    @Mapping(source = "status", target = "statusEntrega")
    EntregaDto toDto(Entrega entrega);


    default List<Long> mapMercadoriasToIds(List<Mercadoria> mercadorias) {
        if (mercadorias == null) {
            return Collections.emptyList();
        }
        return mercadorias.stream()
                .map(Mercadoria::getId)
                .collect(Collectors.toList());
    }
}
