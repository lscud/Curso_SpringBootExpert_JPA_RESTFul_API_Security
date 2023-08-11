package com.lscud.curso.exemploProjeto.service;

import com.lscud.curso.exemploProjeto.domain.entity.Pedido;
import com.lscud.curso.exemploProjeto.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);


}
