package com.lscud.curso.exemploProjeto.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @NotEmpty(message = "campo descrição obrigatorio")
    private String descricao;

    @Column(name = "preco_unitario")
    @NotNull(message = "campo preço obrigatorio")
    private BigDecimal preco;

}
