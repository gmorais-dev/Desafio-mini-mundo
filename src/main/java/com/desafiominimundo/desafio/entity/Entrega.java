package com.desafiominimundo.desafio.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "remetente_id")
    private Cliente remetente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Cliente destinatario;

    @OneToMany(mappedBy = "entrega")
    private List<Mercadoria> mercadorias;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;


}

