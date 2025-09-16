package com.desafiominimundo.desafio.dto;

import com.desafiominimundo.desafio.entity.StatusEntrega;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntregaDto  {
    private Long id;

    private Long remetenteId;
    private Long destinatarioId;
    private List<Long> mercadoriaIds;
    private StatusEntrega statusEntrega;
}