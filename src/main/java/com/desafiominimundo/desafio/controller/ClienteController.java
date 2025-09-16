package com.desafiominimundo.desafio.controller;

import com.desafiominimundo.desafio.dto.ClienteDto;
import com.desafiominimundo.desafio.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/Clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    @PostMapping
    public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody ClienteDto dto) {
        ClienteDto criado = clienteService.cadastrarCliente(dto);
        return new ResponseEntity<>(criado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable Long id,
                                                       @RequestBody ClienteDto dto) {
        ClienteDto atualizado = clienteService.atualizarCliente(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping
    public ResponseEntity<?> listarTodosClientes() {
        return ResponseEntity.ok(clienteService.listarTodosClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscarPorId(@PathVariable Long id) {
        ClienteDto cliente = (ClienteDto) clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
