package com.tech.teste.luiza.oracleebs.service;

import com.tech.teste.luiza.oracleebs.dto.response.AuthenticationResponseRecord;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    AuthenticationResponseRecord authenticate(Authentication authentication);
}
