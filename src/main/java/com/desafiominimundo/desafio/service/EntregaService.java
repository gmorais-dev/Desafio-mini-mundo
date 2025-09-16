package com.desafiominimundo.desafio.service;


import com.desafiominimundo.desafio.Logger.LoggerSingleton;
import com.desafiominimundo.desafio.dto.EntregaDto;
import com.desafiominimundo.desafio.entity.Cliente;
import com.desafiominimundo.desafio.entity.Entrega;
import com.desafiominimundo.desafio.entity.Mercadoria;
import com.desafiominimundo.desafio.entity.StatusEntrega;
import com.desafiominimundo.desafio.exception.BusinessException;
import com.desafiominimundo.desafio.exception.NotFoundException;
import com.desafiominimundo.desafio.mapper.EntregaMapper;
import com.desafiominimundo.desafio.repository.ClienteRepository;
import com.desafiominimundo.desafio.repository.EntregaRepository;
import com.desafiominimundo.desafio.repository.MercadoriaRepository;
import com.desafiominimundo.desafio.util.EntregaValidador;
import com.desafiominimundo.desafio.util.ValidadorTransicaoStatusEntrega;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntregaService {

    private final EntregaRepository repository;
    private final MercadoriaRepository mercadoriaRepository;
    private final EntregaMapper mapper;
    private final ClienteRepository clienteRepository;
    private final EntregaRepository entregaRepository;
    private final EntregaValidador validator;
    private final ValidadorTransicaoStatusEntrega validatorStatus;


    @Transactional
    public EntregaDto cadastrarEntrega(EntregaDto dto) {

        log.info("Cadastrando uma entrega");

        validator.validar(dto);

        Cliente remetente = clienteRepository.findById(dto.getRemetenteId()).get();
        Cliente destinatario = clienteRepository.findById(dto.getDestinatarioId()).get();

        List<Mercadoria> mercadorias = dto.getMercadoriaIds().stream()
                .map(id -> mercadoriaRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Mercadoria não encontrada: " + id)))
                .toList();

        Entrega entrega = Entrega.builder()
                .remetente(remetente)
                .destinatario(destinatario)
                .mercadorias(mercadorias)
                .status(dto.getStatusEntrega() != null ? dto.getStatusEntrega() : StatusEntrega.PENDENTE)
                .build();
        mercadorias.forEach(m -> m.setEntrega(entrega));
        Entrega salvo = repository.save(entrega);
        log.info("Entrega criada: ID {}, Remetente: {}, Destinatário: {}, Mercadorias: {}, Status: {}",
                salvo.getId(),
                remetente.getNomeOuRazaoSocial(),
                destinatario.getNomeOuRazaoSocial(),
                mercadorias.size(),
                salvo.getStatus()
        );
        return mapper.toDto(salvo);
    }



    public EntregaDto buscarEntregaPorId(Long id) {

        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Entrega não encontrada: ID {}", id);
                    return new NotFoundException("Entrega não encontrada com id: " + id);
                });

        return mapper.toDto(entrega);
}

    public List<EntregaDto> listarTodasEntregas() {
        log.info("Listando todas as entregas");

        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public EntregaDto atualizarStatusEntrega(Long id, StatusEntrega novoStatus) {
        log.info("Atualizando status da entrega: {}", id);

        Entrega entrega = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entrega não encontrada com id: " + id));

        StatusEntrega statusAtual = entrega.getStatus();

        validatorStatus.validarTransicao(statusAtual, novoStatus);
        entrega.setStatus(novoStatus);
        Entrega atualizada = repository.save(entrega);
        log.info("Status da entrega {} atualizado de {} para: {}", id, statusAtual, novoStatus);

        return mapper.toDto(atualizada);
    }
    @Transactional
    public void deletarEntrega(Long id) {
        log.info("Solicitação de exclusão da entrega: {}", id);
        Entrega entrega = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entrega não encontrada com id: " + id));
        if (entrega.getStatus() == StatusEntrega.CONCLUIDA) {
            throw new BusinessException("Entrega concluída não pode ser deletada");
        }
        if (entrega.getMercadorias() != null && !entrega.getMercadorias().isEmpty()) {
            entrega.getMercadorias().forEach(m -> m.setEntrega(null));

            mercadoriaRepository.saveAll(entrega.getMercadorias());
        }
        repository.delete(entrega);
        log.info("Entrega deletada: ID {}", id);
        LoggerSingleton.getInstance().log("Entrega deletada: ID " + id);
    }



}

