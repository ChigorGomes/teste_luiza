package com.tech.teste.luiza.oracleebs.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;

/**
 * Serviço responsável pela criação de logs de requisições e respostas HTTP.
 *
 * <p>Define o método para registrar logs baseados nas informações das requisições e respostas.</p>
 *
 * @author Cicero Higor Gomes
 */

public interface LoggingService {
    boolean createLog(HttpServletRequest request, HttpServletResponse response, ContentCachingResponseWrapper responseWrapper) throws UnsupportedEncodingException;


}