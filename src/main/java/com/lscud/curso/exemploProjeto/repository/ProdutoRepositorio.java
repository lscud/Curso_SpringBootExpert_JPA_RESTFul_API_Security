package com.lscud.curso.exemploProjeto.repository;

import com.lscud.curso.exemploProjeto.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {
}
