package com.desafiominimundo.desafio.controller;


import com.desafiominimundo.desafio.dto.EntregaDto;
import com.desafiominimundo.desafio.entity.StatusEntrega;
import com.desafiominimundo.desafio.service.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
@RequiredArgsConstructor
public class EntregaController {

    private final EntregaService entregaService;

    @PostMapping
    public ResponseEntity<EntregaDto> criarEntrega(@RequestBody EntregaDto dto) {
        return ResponseEntity.ok(entregaService.cadastrarEntrega(dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<EntregaDto> atualizarStatusEntrega(
            @PathVariable Long id,
            @RequestParam StatusEntrega status) {

        EntregaDto entregaAtualizada = entregaService.atualizarStatusEntrega(id, status);
        return ResponseEntity.ok(entregaAtualizada);
    }


    @GetMapping
    public ResponseEntity<List<EntregaDto>> listarEntregas() {
        return ResponseEntity.ok(entregaService.listarTodasEntregas());
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntregaDto> buscarEntregaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(entregaService.buscarEntregaPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEntrega(@PathVariable Long id) {
        entregaService.deletarEntrega(id);
        return ResponseEntity.noContent().build();
    }
}

