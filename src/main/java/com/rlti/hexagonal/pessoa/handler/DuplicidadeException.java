package com.rlti.hexagonal.pessoa.handler;

public class DuplicidadeException extends RuntimeException {
    public DuplicidadeException(String message) {
        super(message);
    }
}
