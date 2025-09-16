package com.desafiominimundo.desafio.repository;

import com.desafiominimundo.desafio.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository  extends JpaRepository<Endereco, Long> {
}
