package com.lscud.curso.exemploProjeto.service.impl;

import com.lscud.curso.exemploProjeto.domain.repository.PedidoRepositorio;
import com.lscud.curso.exemploProjeto.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;


}
