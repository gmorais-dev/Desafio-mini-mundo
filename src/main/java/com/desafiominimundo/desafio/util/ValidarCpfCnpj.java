package com.desafiominimundo.desafio.util;

import com.desafiominimundo.desafio.Validation.ClienteValidacaoStrategy;
import com.desafiominimundo.desafio.dto.ClienteDto;
import com.desafiominimundo.desafio.exception.BusinessException;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidarCpfCnpj implements ClienteValidacaoStrategy{


    public  void validar(ClienteDto dto) {
        if (dto.getCpfOuCnpj() == null || dto.getCpfOuCnpj().isBlank()) {
            throw new ValidationException("CPF ou CNPJ inválido: vazio");
        }

        String remover = dto.getCpfOuCnpj().replaceAll("\\D", "");
        if (remover.length() == 11) {
            if (remover.matches("(\\d)\\1{10}")) {
                throw new ValidationException("CPF inválido: todos os dígitos iguais");
            }

        } else if (remover.length() == 14) {
            if (remover.matches("(\\d)\\1{13}")) {
                throw new BusinessException("CNPJ inválido: todos os dígitos iguais");
            }
        } else {
            throw new ValidationException("CPF ou CNPJ inválido: quantidade de dígitos incorreta");
        }



    }
}

