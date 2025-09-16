package com.desafiominimundo.desafio.util;

import com.desafiominimundo.desafio.Validation.EntregaValidacaoStrategy;
import com.desafiominimundo.desafio.dto.EntregaDto;
import com.desafiominimundo.desafio.entity.StatusEntrega;
import com.desafiominimundo.desafio.exception.BusinessException;
import org.springframework.stereotype.Component;


@Component
public class ValidadorTransicaoStatusEntrega implements EntregaValidacaoStrategy {

        public void validarTransicao(StatusEntrega atual, StatusEntrega novo) {
            if (atual == null) {
                throw new BusinessException("Status atual não pode ser nulo");
            }

            switch (atual) {
                case PENDENTE:
                    if (novo == StatusEntrega.CONCLUIDA) {
                        throw new BusinessException("Não é permitido ir de PENDENTE direto para CONCLUIDA");
                    }
                    break;
                case EM_ANDAMENTO:
                    if (novo == StatusEntrega.PENDENTE) {
                        throw new BusinessException("Não é permitido voltar para PENDENTE");
                    }
                    break;
                case CONCLUIDA:
                case CANCELADA:
                    throw new BusinessException("Status final não pode ser alterado");
            }
        }

    @Override
    public void validarEntregas(EntregaDto dto) {

    }
}
