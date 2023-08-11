package com.lscud.curso.exemploProjeto.rest.controller;

import com.lscud.curso.exemploProjeto.exception.RegraNegocioException;
import com.lscud.curso.exemploProjeto.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String messageError = ex.getMessage();
        return new ApiErrors(messageError);
    }

}
