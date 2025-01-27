package com.tech.teste.luiza.oracleebs.config.rabbit;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueDeciderImpl implements QueueDecider {
    private final String errorQueue;
    private final String sucessQueue;

    public QueueDeciderImpl(@Value("${queue.name.error}") String errorQueue,
                            @Value("${queue.name.success}") String sucessQueue) {
        this.errorQueue = errorQueue;
        this.sucessQueue = sucessQueue;
    }


    @Override
    public String decideQueue(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        return (request.getMethod().equals("POST") && (httpServletResponse.getStatus()>= 200 && httpServletResponse.getStatus() <= 299))
                ? sucessQueue
                : errorQueue;

    }
}
