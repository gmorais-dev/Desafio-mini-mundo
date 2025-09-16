package com.desafiominimundo.desafio.util;

import com.desafiominimundo.desafio.Validation.MercadoriaValidacaoStrategy;
import com.desafiominimundo.desafio.dto.MercadoriaDto;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidarMercadoria  implements MercadoriaValidacaoStrategy {
    @Override
    public void validarMercadorias(MercadoriaDto dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new ValidationException("Nome da mercadoria é obrigatório");
        }

        if (dto.getValor() == null || dto.getValor() <= 0) {
            throw new ValidationException("Valor da mercadoria deve ser maior que zero");
        }

        if (dto.getPeso() == null || dto.getPeso() <= 0) {
            throw new ValidationException("Peso da mercadoria deve ser maior que zero");
        }

        if (dto.getVolume() == null || dto.getVolume() <= 0) {
            throw new ValidationException("Volume da mercadoria deve ser maior que zero");
        }

    }
}