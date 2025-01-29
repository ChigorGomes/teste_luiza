package com.tech.teste.luiza.oracleebs.exception;

import java.time.Instant;

/**
 * Representa um erro customizado com informações detalhadas.
 *
 * <p>Inclui timestamp, status HTTP, mensagem de erro, caminho da requisição
 * e o método HTTP associado.</p>
 *
 * @param timestamp O momento em que o erro ocorreu.
 * @param status O código de status HTTP do erro.
 * @param error A mensagem de erro.
 * @param path O caminho da requisição que gerou o erro.
 * @param method O método HTTP utilizado na requisição.
 *
 * @author Cicero Higor Gomes
 */
public record CustomError(Instant timestamp, Integer status, String error, String path, String method) {
}
