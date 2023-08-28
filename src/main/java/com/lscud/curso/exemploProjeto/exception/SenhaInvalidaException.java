package com.lscud.curso.exemploProjeto.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(){
        super("Senha inválida");
    }
}
