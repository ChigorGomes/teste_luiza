package com.tech.teste.luiza.oracleebs.config.rabbit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

import static com.tech.teste.luiza.oracleebs.util.HttpResponseProcessor.convertToJsonNodeResponseBody;

@Component
public class QueueSender implements MessageSender{
    private RabbitTemplate rabbitTemplate;
    private Queue queue;

    public QueueSender(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    @Override
    public void send(ContentCachingResponseWrapper responseWrapper) throws IOException {
        JsonNode responseBody = convertToJsonNodeResponseBody(responseWrapper);
        rabbitTemplate.convertAndSend(this.queue.getName(), responseBody);
    }

}
