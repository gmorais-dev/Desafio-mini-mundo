package com.desafiominimundo.desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MercadoriaDto {
    private Long id;
    private String nome;
    private Double peso;
    private Double volume;
    private Double valor;
}