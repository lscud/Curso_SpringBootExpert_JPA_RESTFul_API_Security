package com.lscud.curso.exemploProjeto.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(){
        super("Pedido nao encontrado!");
    }
}
