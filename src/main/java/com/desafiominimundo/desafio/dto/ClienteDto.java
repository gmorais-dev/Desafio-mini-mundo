package com.desafiominimundo.desafio.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDto {

    private Long id;
    private String nomeOuRazaoSocial;
    private String cpfOuCnpj;
    private EnderecoDto endereco;
}
