package com.tech.teste.luiza.oracleebs.exception;

import java.time.Instant;
import java.util.List;

/**
 * Representa um erro relacionado a campos específicos com informações detalhadas.
 *
 * <p>Extende o erro customizado adicionando uma lista de campos que causaram o problema.</p>
 *
 * @param timestamp O momento em que o erro ocorreu.
 * @param status O código de status HTTP do erro.
 * @param error A mensagem de erro.
 * @param path O caminho da requisição que gerou o erro.
 * @param method O método HTTP utilizado na requisição.
 * @param fields A lista de campos relacionados ao erro.
 *
 * @author Cicero Higor Gomes
 */
public record FieldError(Instant timestamp, Integer status, String error, String path, String method, List<String> fields) {

    public FieldError(CustomError customError, List<String> fields){
        this(customError.timestamp(),customError.status(),customError.error(),customError.path(),customError.method(),fields);
    }

}
