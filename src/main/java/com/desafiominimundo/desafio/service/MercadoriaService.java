package com.desafiominimundo.desafio.service;

import com.desafiominimundo.desafio.dto.MercadoriaDto;
import com.desafiominimundo.desafio.entity.Mercadoria;
import com.desafiominimundo.desafio.exception.DuplicityException;
import com.desafiominimundo.desafio.exception.NotFoundException;
import com.desafiominimundo.desafio.mapper.MercadoriaMapper;
import com.desafiominimundo.desafio.repository.MercadoriaRepository;
import com.desafiominimundo.desafio.util.MercadoriaValidador;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MercadoriaService {

    private final MercadoriaRepository repository;
    private final MercadoriaMapper mapper;
    private final MercadoriaValidador validador;

    public MercadoriaDto cadastrar(MercadoriaDto dto) {
        validador.validar(dto);

        if (repository.existsByNome(dto.getNome())) {
            throw new DuplicityException("Mercadoria já cadastrada: " + dto.getNome());
        }

        Mercadoria mercadoria = mapper.toEntity(dto);
        Mercadoria salvo = repository.save(mercadoria);
        log.info("Mercadoria cadastrada: {}", salvo.getNome());
        return mapper.toDto(salvo);
    }
    public MercadoriaDto atualizar(Long id, MercadoriaDto dto) {
        Mercadoria existente = repository.findById(id).orElseThrow(() -> new NotFoundException("Mercadoria não encontrada com id: " + id));

        validador.validar(dto);
        existente.setNome(dto.getNome());
        existente.setPeso(dto.getPeso());
        existente.setVolume(dto.getVolume());
        existente.setValor(dto.getValor());

        Mercadoria atualizado = repository.save(existente);
        log.info("Mercadoria atualizada: {}", atualizado.getNome());
        return mapper.toDto(atualizado);
    }
    public List<MercadoriaDto> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    public MercadoriaDto buscarPorId(Long id) {
        Mercadoria mercadoria = repository.findById(id).orElseThrow(() -> new NotFoundException("Mercadoria não encontrada com id: " + id));
        return mapper.toDto(mercadoria);
    }
    public void deletar(Long id) {
        Mercadoria mercadoria = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mercadoria não encontrada com id: " + id));
        log.info("Mercadoria deletada: {}", mercadoria.getNome());
        repository.delete(mercadoria);
    }
}




