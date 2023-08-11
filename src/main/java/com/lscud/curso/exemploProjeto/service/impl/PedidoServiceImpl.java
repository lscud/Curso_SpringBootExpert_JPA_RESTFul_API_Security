package com.lscud.curso.exemploProjeto.service.impl;

import com.lscud.curso.exemploProjeto.domain.entity.Cliente;
import com.lscud.curso.exemploProjeto.domain.entity.ItemPedido;
import com.lscud.curso.exemploProjeto.domain.entity.Pedido;
import com.lscud.curso.exemploProjeto.domain.entity.Produto;
import com.lscud.curso.exemploProjeto.domain.enums.StatusPedido;
import com.lscud.curso.exemploProjeto.domain.repository.ClienteRepositorio;
import com.lscud.curso.exemploProjeto.domain.repository.ItemPedidoRepositorio;
import com.lscud.curso.exemploProjeto.domain.repository.PedidoRepositorio;
import com.lscud.curso.exemploProjeto.domain.repository.ProdutoRepositorio;
import com.lscud.curso.exemploProjeto.exception.PedidoNaoEncontradoException;
import com.lscud.curso.exemploProjeto.exception.RegraNegocioException;
import com.lscud.curso.exemploProjeto.rest.dto.ItemsPedidoDTO;
import com.lscud.curso.exemploProjeto.rest.dto.PedidoDTO;
import com.lscud.curso.exemploProjeto.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //faz gerar um construtor com todos os argumentos obrigatorios que são os que tem "final"
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepositorio pedidoRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ProdutoRepositorio produtoRepositorio;
    private final ItemPedidoRepositorio itemPedidoRepositorio;


    @Override
    @Transactional // com essa anotation como tenho persistencia em diferentes tabelas do banco ou dá certo tudo ou o processo é abortado. Ou faz tudo ou nao faz nada
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepositorio.findById(idCliente).orElseThrow(() -> new RegraNegocioException("codigo de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedidos = converterItems(pedido, dto.getItems());
        pedidoRepositorio.save(pedido);
        itemPedidoRepositorio.saveAll(itemsPedidos);
        pedido.setItens(itemsPedidos);
        return pedido;


    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepositorio.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidoRepositorio.findById(id).map(pedido -> {
            pedido.setStatus(statusPedido);
            return pedidoRepositorio.save(pedido);

        }).orElseThrow(()-> new PedidoNaoEncontradoException() );
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemsPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar um pedido sem itens.");
        }
        return items.stream().map(dto ->{
            Integer idProduto = dto.getProduto();
            Produto produto = produtoRepositorio.findById(idProduto).orElseThrow(() -> new RegraNegocioException("codigo de produto inválido: " + idProduto));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            return itemPedido;
        }).collect(Collectors.toList());
    }
}
