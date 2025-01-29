package com.tech.teste.luiza.oracleebs.service;

import org.springframework.security.core.Authentication;
/**
 * Serviço responsável por operações relacionadas a tokens JWT.
 *
 * <p>Oferece um método para gerar tokens com base em dados de autenticação.</p>
 *
 * @author Cicero Higor Gomes
 */
public interface JwtService {
    String generateToken(Authentication authentication);
}
