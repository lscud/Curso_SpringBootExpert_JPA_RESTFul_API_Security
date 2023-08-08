package com.lscud.curso.exemploProjeto;

import com.lscud.curso.exemploProjeto.entity.Cliente;
import com.lscud.curso.exemploProjeto.repository.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ExemploProjetoApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepositorio clienteRepositorio){
		return args ->{
			clienteRepositorio.salvar(new Cliente("Douglas"));
			clienteRepositorio.salvar(new Cliente("Outro Cliente"));

			List<Cliente> lista = clienteRepositorio.obterTodos();
			lista.forEach(System.out::println);

			lista.forEach(c -> {
				c.setNome(c.getNome() + " atualizado.");
				clienteRepositorio.atualizar(c);
			});

			clienteRepositorio.buscarPorNome("Cli").forEach(System.out::println);

			lista = clienteRepositorio.obterTodos();
			lista.forEach(System.out::println);

			clienteRepositorio.obterTodos().forEach(c -> clienteRepositorio.deletar(c));

			lista = clienteRepositorio.obterTodos();
			if (lista.isEmpty()) {
				System.out.println("Nenhum cliente encontrado");
			} else {
				lista.forEach(System.out::println);
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ExemploProjetoApplication.class, args);
	}

}
