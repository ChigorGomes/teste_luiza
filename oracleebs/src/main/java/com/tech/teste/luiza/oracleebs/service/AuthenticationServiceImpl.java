package com.tech.teste.luiza.oracleebs.service;

import com.tech.teste.luiza.oracleebs.dto.response.AuthenticationResponseRecord;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtService jwtService;


    public AuthenticationServiceImpl(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public AuthenticationResponseRecord authenticate(Authentication authentication) {
        System.out.println(authentication);
        return new AuthenticationResponseRecord(
                jwtService.generateToken(authentication),
                "Bearer",
                3600L
        );
    }
}
