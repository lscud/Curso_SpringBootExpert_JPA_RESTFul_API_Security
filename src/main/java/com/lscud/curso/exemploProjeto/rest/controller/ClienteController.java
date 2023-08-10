package com.lscud.curso.exemploProjeto.rest.controller;

import com.lscud.curso.exemploProjeto.entity.Cliente;
import com.lscud.curso.exemploProjeto.repository.ClienteRepositorio;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepositorio  clienteRepositorio;
    @GetMapping(value = "{id}")
    public Cliente getClienteById(@PathVariable("id") Integer id ){
        return clienteRepositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente nao encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente){
        return clienteRepositorio.save(cliente);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        Cliente cliente = clienteRepositorio.findById(id).map(c -> {
            clienteRepositorio.delete(c);
            return c;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente nao encontrado"));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody  Cliente cliente, @PathVariable("id") Integer id){
        Cliente clienteFounded = clienteRepositorio.findById(id).map(c -> {
            cliente.setId(c.getId());
            clienteRepositorio.save(cliente);
            return c;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente nao encontrado"));
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro){ //dessa forma usando "EXAMPLE" a busca fica mais flexivel. Vc pode buscar por nome, por uma parte do nome, por cpf...Voce vai acrescentando filtros e a busca vai se tornando mais refinada
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return clienteRepositorio.findAll(example);
    }
}
