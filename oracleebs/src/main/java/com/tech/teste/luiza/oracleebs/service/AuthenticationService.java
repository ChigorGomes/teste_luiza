package com.tech.teste.luiza.oracleebs.service;

import com.tech.teste.luiza.oracleebs.dto.response.AuthenticationResponseRecord;
import org.springframework.security.core.Authentication;

/**
 * Serviço responsável pelo processo de autenticação.
 *
 * <p>Define um método para autenticar uma solicitação e retornar uma resposta de autenticação.</p>
 *
 * @author Cicero Higor Gomes
 */
public interface AuthenticationService {
    AuthenticationResponseRecord authenticate(Authentication authentication);
}
