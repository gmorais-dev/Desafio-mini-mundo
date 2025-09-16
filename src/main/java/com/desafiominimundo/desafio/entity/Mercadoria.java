package com.desafiominimundo.desafio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "mercadorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mercadoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double peso;
    private Double volume;
    private Double valor;
    @ManyToOne
    @JoinColumn(name = "entrega_id")
    private Entrega entrega;
}