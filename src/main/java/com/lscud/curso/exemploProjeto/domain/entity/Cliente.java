package com.lscud.curso.exemploProjeto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente") //nao precisa mas estou colocando para saber que poderia usar caso o nome da tabela fosse diferente do nome do objeto
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") //nao precisa mas estou colocando para saber que poderia usar caso o nome da coluna na tabela fosse diferente.
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)  //PArametro padrão que faz com que os pedidos nao sejam carregados ao consultar um cliente
    private Set<Pedido>  pedidos;

    @Column(name = "cpf", length = 11)
    private String cpf;


    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
