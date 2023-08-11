package com.lscud.curso.exemploProjeto.domain.repository;

import com.lscud.curso.exemploProjeto.domain.entity.Cliente;
import com.lscud.curso.exemploProjeto.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PedidoRepositorio extends JpaRepository<Pedido, Repository> {

    List<Pedido> findByCliente(Cliente cliente);

}
