package com.lscud.curso.exemploProjeto.domain.repository;

import com.lscud.curso.exemploProjeto.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {
}
