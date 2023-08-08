package com.lscud.curso.exemploProjeto.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente") //nao precisa mas estou colocando para saber que poderia usar caso o nome da tabela fosse diferente do nome do objeto
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") //nao precisa mas estou colocando para saber que poderia usar caso o nome da coluna na tabela fosse diferente.
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    public Cliente() {
    }


    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
