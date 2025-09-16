package com.desafiominimundo.desafio.util;

import com.desafiominimundo.desafio.Validation.ClienteValidacaoStrategy;
import com.desafiominimundo.desafio.dto.ClienteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
@RequiredArgsConstructor

public class ClienteValidator {
    private final List<ClienteValidacaoStrategy> estrategias;
    public void validar(ClienteDto dto) {
        estrategias.forEach(e -> {
            e.validar(dto);
        });
    }
}
