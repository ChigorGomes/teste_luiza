package com.tech.teste.luiza.oracleebs.config.rabbit;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interface responsável por definir a lógica de determinação da fila RabbitMQ adequada
 * para uma requisição e resposta HTTP.
 *
 * <p>A implementação desta interface deve decidir, com base nos dados da requisição
 * ({@link HttpServletRequest}) e resposta ({@link HttpServletResponse}),
 * a fila na qual a mensagem será enviada.</p>
 *
 * @author Cicero Higor Gomes
 */
public interface QueueDecider {
    String decideQueue(HttpServletRequest request, HttpServletResponse httpServletResponse);
}