package com.tech.teste.luiza.oracleebs.config.rabbit;

import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

public interface MessageSender {
    void send(ContentCachingResponseWrapper responseWrapper) throws IOException;
}
