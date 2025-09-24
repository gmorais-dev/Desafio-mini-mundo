package com.desafiominimundo.desafio.service;

import com.desafiominimundo.desafio.dto.ClienteDto;
import com.desafiominimundo.desafio.entity.Cliente;
import com.desafiominimundo.desafio.entity.Endereco;

import com.desafiominimundo.desafio.exception.DuplicityException;
import com.desafiominimundo.desafio.exception.NotFoundException;
import com.desafiominimundo.desafio.mapper.ClienteMapper;
import com.desafiominimundo.desafio.mapper.EnderecoMapper;
import com.desafiominimundo.desafio.repository.ClienteRepository;
import com.desafiominimundo.desafio.util.ClienteValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;
    private final ClienteValidator validator;

    public ClienteDto cadastrarCliente(ClienteDto dto) {
        validator.validar(dto);
        if (repository.existsByCpfOuCnpj(dto.getCpfOuCnpj())) {
            throw new DuplicityException("CPF ou CNPJ já cadastrado: " + dto.getCpfOuCnpj()
            );
        }
        Cliente cliente = mapper.toEntity(dto);
        Cliente salvo = repository.save(cliente);
        log.info("Cliente criado: {}", salvo.getNomeOuRazaoSocial());
        return mapper.toDto(salvo);
    }

    public ClienteDto atualizarCliente(Long id, ClienteDto dto) {
        Cliente existente = repository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado com id: " + id));
        validator.validar(dto);
        existente.setNomeOuRazaoSocial(dto.getNomeOuRazaoSocial());
        existente.setCpfOuCnpj(dto.getCpfOuCnpj());

        if (dto.getEndereco() != null) {
            Endereco enderecoAtualizado = EnderecoMapper.INSTANCE.toEntity(dto.getEndereco());
            enderecoAtualizado.setId(existente.getEndereco() != null ? existente.getEndereco().getId() : null);
            existente.setEndereco(enderecoAtualizado);
        }
        Cliente atualizado = repository.save(existente);
        log.info("Cliente atualizado: {}", atualizado.getNomeOuRazaoSocial());
        return mapper.toDto(atualizado);
    }

    public List<ClienteDto> listarTodosClientes() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

        public ClienteDto buscarPorId(Long id) {
            Cliente cliente = repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Cliente não encontrado com id: " + id));
            return mapper.toDto(cliente);
        }

        public void deletarCliente(Long id) {
            Cliente cliente = repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Cliente não encontrado com id: " + id));
            repository.delete(cliente);
            log.info("Cliente deletado: {}", cliente.getNomeOuRazaoSocial());
        }
}