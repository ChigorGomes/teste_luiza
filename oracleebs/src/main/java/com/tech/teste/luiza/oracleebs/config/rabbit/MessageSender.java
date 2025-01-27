package com.tech.teste.luiza.oracleebs.config.rabbit;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

public interface MessageSender {
     void send(HttpServletRequest httpRequest, HttpServletResponse httpResponse, ContentCachingResponseWrapper responseWrapper) throws IOException;
}
