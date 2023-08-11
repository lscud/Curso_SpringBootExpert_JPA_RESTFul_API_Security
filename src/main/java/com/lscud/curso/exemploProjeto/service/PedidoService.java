package com.lscud.curso.exemploProjeto.service;

import com.lscud.curso.exemploProjeto.domain.entity.Pedido;
import com.lscud.curso.exemploProjeto.domain.enums.StatusPedido;
import com.lscud.curso.exemploProjeto.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);


}
