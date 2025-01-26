package com.tech.teste.luiza.oracleebs.config.filter;

import com.tech.teste.luiza.oracleebs.config.rabbit.QueueSender;
import com.tech.teste.luiza.oracleebs.service.LoggingServiceImpl;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
public class RequestLoggingFilter implements Filter {

    private final LoggingServiceImpl loggingServiceImpl;
    private final QueueSender queueSender;

    public RequestLoggingFilter(LoggingServiceImpl loggingServiceImpl, QueueSender queueSender) {
        this.loggingServiceImpl = loggingServiceImpl;
        this.queueSender = queueSender;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, responseWrapper);
        loggingServiceImpl.createLog(httpRequest, httpResponse, responseWrapper);
        queueSender.send(responseWrapper);
        responseWrapper.copyBodyToResponse();



    }
}
