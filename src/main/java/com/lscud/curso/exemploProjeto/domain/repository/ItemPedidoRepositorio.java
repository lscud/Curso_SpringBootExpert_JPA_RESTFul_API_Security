package com.lscud.curso.exemploProjeto.domain.repository;

import com.lscud.curso.exemploProjeto.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepositorio extends JpaRepository<ItemPedido, Integer> {

}
