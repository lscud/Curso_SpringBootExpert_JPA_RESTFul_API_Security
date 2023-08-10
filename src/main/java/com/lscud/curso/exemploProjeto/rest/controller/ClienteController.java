package com.lscud.curso.exemploProjeto.rest.controller;

import com.lscud.curso.exemploProjeto.entity.Cliente;
import com.lscud.curso.exemploProjeto.repository.ClienteRepositorio;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id ){
        Optional<Cliente> cliente = clienteRepositorio.findById(id);
        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        Cliente clienteSaved = clienteRepositorio.save(cliente);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();
        return ResponseEntity.created(location).body(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        Optional<Cliente> clienteFounded = clienteRepositorio.findById(id);
        if(clienteFounded.isPresent()){
            clienteRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@RequestBody  Cliente cliente, @PathVariable("id") Integer id){
        Optional<Cliente> clienteFounded = clienteRepositorio.findById(id);
        if(clienteFounded.isPresent()){
            cliente.setId(clienteFounded.get().getId());
            Cliente saved = clienteRepositorio.save(cliente);
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity find(Cliente filtro){ //dessa forma usando example a busca fica mais flexivel. Vc pode buscar por nome, por uma parte do nome, por cpf...
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        List<Cliente> lista = clienteRepositorio.findAll(example);
        return ResponseEntity.ok(lista);

    }
}
