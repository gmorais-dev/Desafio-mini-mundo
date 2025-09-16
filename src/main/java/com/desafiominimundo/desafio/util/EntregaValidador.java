package com.desafiominimundo.desafio.util;

import com.desafiominimundo.desafio.Validation.EntregaValidacaoStrategy;
import com.desafiominimundo.desafio.dto.EntregaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor

public class EntregaValidador  {
    private final List<EntregaValidacaoStrategy> estrategias;
    public  void validar(EntregaDto dto) {
        estrategias.forEach(e -> e.validarEntregas(dto));
    }
}








