package com.tech.teste.luiza.oracleebs.config.rabbit;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
/**
 * Interface para envio de mensagens baseadas em dados de uma requisição HTTP.
 *
 * <p>Define um contrato para implementar o envio de mensagens utilizando
 * informações provenientes de uma requisição ({@link HttpServletRequest}),
 * resposta ({@link HttpServletResponse}) e do corpo da resposta
 * encapsulada em {@link ContentCachingResponseWrapper}.</p>
 *
 * @author Cicero Higor Gomes
 */
public interface MessageSender {
     void send(HttpServletRequest httpRequest, HttpServletResponse httpResponse, ContentCachingResponseWrapper responseWrapper) throws IOException;
}
