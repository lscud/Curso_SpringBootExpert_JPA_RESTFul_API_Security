package com.lscud.curso.exemploProjeto;

import com.lscud.curso.exemploProjeto.domain.entity.Cliente;
import com.lscud.curso.exemploProjeto.domain.entity.Pedido;
import com.lscud.curso.exemploProjeto.domain.repository.ClienteRepositorio;
import com.lscud.curso.exemploProjeto.domain.repository.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class ExemploProjetoApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepositorio clienteRepositorio, @Autowired PedidoRepositorio pedidoRepositorio){
		return args ->{
			Cliente cliente = clienteRepositorio.save(new Cliente("Douglas"));
			clienteRepositorio.save(new Cliente("Outro Cliente"));

			Pedido p = new Pedido();
			p.setCliente(cliente);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));

			pedidoRepositorio.save(p);

//			Cliente cliente_encontrado = clienteRepositorio.findClienteFecthPedidos(cliente.getId());
//			System.out.println(cliente_encontrado);
//			System.out.println(cliente_encontrado.getPedidos());

			pedidoRepositorio.findByCliente(cliente).forEach(System.out::println);



		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ExemploProjetoApplication.class, args);
	}

}
