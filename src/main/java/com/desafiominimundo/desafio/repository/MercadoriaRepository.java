package com.desafiominimundo.desafio.repository;

import com.desafiominimundo.desafio.entity.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long> {
    boolean existsByNome(String nome);
}
