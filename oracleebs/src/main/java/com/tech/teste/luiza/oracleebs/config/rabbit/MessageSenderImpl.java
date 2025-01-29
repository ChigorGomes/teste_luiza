package com.tech.teste.luiza.oracleebs.config.rabbit;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

import static com.tech.teste.luiza.oracleebs.util.HttpResponseProcessor.convertToJsonNodeResponseBody;

/**
 * Implementação da interface {@link MessageSender} responsável por enviar mensagens
 * para a fila RabbitMQ com base nos dados da requisição e resposta HTTP.
 *
 * <p>Esta classe utiliza o {@link RabbitTemplate} para enviar as mensagens
 * e o {@link QueueDecider} para determinar a fila apropriada para cada requisição.</p>
 *
 * @author Cicero Higor Gomes
 */
@Component
public class MessageSenderImpl implements MessageSender{
    private final RabbitTemplate rabbitTemplate;
    private final QueueDecider queueDecider;



    public MessageSenderImpl(RabbitTemplate rabbitTemplate, QueueDecider queueDecider) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueDecider = queueDecider;
    }


    @Override
    public void send(HttpServletRequest httpRequest, HttpServletResponse httpResponse, ContentCachingResponseWrapper responseWrapper) throws IOException {
        JsonNode responseBody = convertToJsonNodeResponseBody(responseWrapper);
        String queue= queueDecider.decideQueue(httpRequest,httpResponse);
        rabbitTemplate.convertAndSend(queue, responseBody);
    }




}
