package com.tech.teste.luiza.oracleebs.exception;


/**
 * Exceção personalizada para cenários em que um recurso não é encontrado.
 *
 * <p>Lança uma exceção de runtime com uma mensagem específica indicando
 * que o recurso não foi encontrado.</p>
 *
 * @author Cicero Higor Gomes
 */
public class NotFoundExceptionHandler extends RuntimeException {
    public NotFoundExceptionHandler(String message) {
        super(message + " not found");
    }
}
