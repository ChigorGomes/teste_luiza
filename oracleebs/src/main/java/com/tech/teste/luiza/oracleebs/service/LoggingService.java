package com.tech.teste.luiza.oracleebs.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;

public interface LoggingService {
    boolean createLog(HttpServletRequest request, HttpServletResponse response, ContentCachingResponseWrapper responseWrapper) throws UnsupportedEncodingException;


}