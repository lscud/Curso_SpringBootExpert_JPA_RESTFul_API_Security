package com.lscud.curso.exemploProjeto.rest.controller;

import com.lscud.curso.exemploProjeto.domain.entity.Pedido;
import com.lscud.curso.exemploProjeto.rest.dto.PedidoDTO;
import com.lscud.curso.exemploProjeto.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save (@RequestBody PedidoDTO dto){
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();

    }



}
