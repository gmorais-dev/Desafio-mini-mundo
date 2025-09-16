package com.desafiominimundo.desafio.util;

import com.desafiominimundo.desafio.Validation.EntregaValidacaoStrategy;
import com.desafiominimundo.desafio.dto.EntregaDto;
import com.desafiominimundo.desafio.entity.Cliente;
import com.desafiominimundo.desafio.exception.BusinessException;
import com.desafiominimundo.desafio.exception.NotFoundException;
import com.desafiominimundo.desafio.repository.ClienteRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
@Component
public class ValidarRemetenteDestinatario implements EntregaValidacaoStrategy {
    private final ClienteRepository clienteRepository;

    @Override
    public void validarEntregas(EntregaDto dto) {
        Cliente remetente = clienteRepository.findById(dto.getRemetenteId())
                .orElseThrow(() -> new NotFoundException("Remetente não encontrado"));

        Cliente destinatario = clienteRepository.findById(dto.getDestinatarioId())
                .orElseThrow(() -> new NotFoundException("Destinatário não encontrado"));

        if (remetente.getId().equals(destinatario.getId())) {
            throw new BusinessException("Remetente e destinatário não podem ser iguais");
        }
        if (dto.getMercadoriaIds() == null || dto.getMercadoriaIds().isEmpty()) {
            throw new ValidationException("A entrega deve conter pelo menos uma mercadoria");
        }

        Set<Long> uniqueIds = new HashSet<>(dto.getMercadoriaIds());
        if (uniqueIds.size() != dto.getMercadoriaIds().size()) {
            throw new ValidationException("Não é permitido adicionar a mesma mercadoria mais de uma vez na entrega");
        }
    }

    }
