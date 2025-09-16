package com.desafiominimundo.desafio.repository;

import com.desafiominimundo.desafio.entity.Entrega;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
