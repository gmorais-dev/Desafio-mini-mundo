package com.desafiominimundo.desafio.util;

import com.desafiominimundo.desafio.Validation.MercadoriaValidacaoStrategy;
import com.desafiominimundo.desafio.dto.MercadoriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MercadoriaValidador {

    private final List<MercadoriaValidacaoStrategy> estrategias;

    public void validar(MercadoriaDto dto) {
        estrategias.forEach(e -> e.validarMercadorias(dto));
    }
}




