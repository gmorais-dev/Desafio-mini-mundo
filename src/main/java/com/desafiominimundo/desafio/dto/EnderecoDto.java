package com.desafiominimundo.desafio.dto;



import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoDto  {
    private Long id;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}