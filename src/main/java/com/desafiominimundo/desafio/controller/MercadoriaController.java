package com.desafiominimundo.desafio.controller;

import com.desafiominimundo.desafio.dto.MercadoriaDto;
import com.desafiominimundo.desafio.service.MercadoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mercadorias")
@RequiredArgsConstructor
public class MercadoriaController {

    private final MercadoriaService service;

    @PostMapping
    public ResponseEntity<MercadoriaDto> cadastrar(@RequestBody MercadoriaDto dto) {
        return new ResponseEntity<>(service.cadastrar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MercadoriaDto> atualizar(@PathVariable Long id, @RequestBody MercadoriaDto dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<MercadoriaDto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MercadoriaDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
