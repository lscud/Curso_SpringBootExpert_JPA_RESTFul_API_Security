package com.lscud.curso.exemploProjeto.rest.controller;

import com.lscud.curso.exemploProjeto.domain.entity.ItemPedido;
import com.lscud.curso.exemploProjeto.domain.entity.Pedido;
import com.lscud.curso.exemploProjeto.domain.enums.StatusPedido;
import com.lscud.curso.exemploProjeto.exception.RegraNegocioException;
import com.lscud.curso.exemploProjeto.rest.dto.AtualizacaoStatusPedidoDTO;
import com.lscud.curso.exemploProjeto.rest.dto.InformacaoItemPedidoDTO;
import com.lscud.curso.exemploProjeto.rest.dto.InformacoesPedidoDTO;
import com.lscud.curso.exemploProjeto.rest.dto.PedidoDTO;
import com.lscud.curso.exemploProjeto.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable("id") Integer id){
        return pedidoService.obterPedidoCompleto(id).map(p -> converter(p)).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "pedido nao encontrado."));
    }

    @PatchMapping("{id}") //quando quero atualizar apenas uma parte
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable("id") Integer id, @RequestBody  AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name()) //name pega o enum e converte string
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(item -> InformacaoItemPedidoDTO.builder().descricaoProduto(item.getProduto().getDescricao())
                .precoUnitario(item.getProduto().getPreco())
                .quantidade(item.getQuantidade()).build()).collect(Collectors.toList());

    }



}
