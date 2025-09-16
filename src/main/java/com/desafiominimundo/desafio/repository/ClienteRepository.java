package com.desafiominimundo.desafio.repository;

import com.desafiominimundo.desafio.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpfOuCnpj(String cpfOuCnpj);
}
