package com.lscud.curso.exemploProjeto.rest.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 *
 * {
 *     "cliente": 1,
 *     "total": 100,
 *     "items": [
 *         {
 *             "produto": 1,
 *             "quantidade": 10
 *         }
 *     ]
 * }
 *
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    @NotNull(message = "Informe codigo do cliente")
    private Integer cliente;
    @NotNull(message = "Campo 'total' obrigatorio")
    private BigDecimal total;

    private List<ItemsPedidoDTO> items;


}
