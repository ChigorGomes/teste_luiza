package com.tech.teste.luiza.oracleebs.config.rabbit;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

import static com.tech.teste.luiza.oracleebs.util.HttpResponseProcessor.convertToJsonNodeResponseBody;

@Component
public class QueueSender implements MessageSender{
    private RabbitTemplate rabbitTemplate;
    private final QueueDecider queueDecider;

    public QueueSender(RabbitTemplate rabbitTemplate, QueueDecider queueDecider) {
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
