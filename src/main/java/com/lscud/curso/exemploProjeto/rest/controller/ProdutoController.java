package com.lscud.curso.exemploProjeto.rest.controller;

import com.lscud.curso.exemploProjeto.entity.Cliente;
import com.lscud.curso.exemploProjeto.entity.Produto;
import com.lscud.curso.exemploProjeto.repository.ClienteRepositorio;
import com.lscud.curso.exemploProjeto.repository.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;
    @GetMapping(value = "{id}")
    public Produto getProdutoById(@PathVariable("id") Integer id ){
        return produtoRepositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto){
        return produtoRepositorio.save(produto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        Produto produto = produtoRepositorio.findById(id).map(p -> {
            produtoRepositorio.delete(p);
            return p;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody  Produto produto, @PathVariable("id") Integer id){
        Produto produtoFounded = produtoRepositorio.findById(id).map(p -> {
            produto.setId(p.getId());
            produtoRepositorio.save(produto);
            return p;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente nao encontrado"));
    }

    @GetMapping
    public List<Produto> find(Produto filtro){ //dessa forma usando "EXAMPLE" a busca fica mais flexivel. Vc pode buscar por nome, por uma parte do nome, por cpf...Voce vai acrescentando filtros e a busca vai se tornando mais refinada
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return produtoRepositorio.findAll(example);
    }
}
