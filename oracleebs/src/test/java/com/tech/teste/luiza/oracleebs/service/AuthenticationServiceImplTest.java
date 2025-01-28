package com.tech.teste.luiza.oracleebs.service;

import com.tech.teste.luiza.oracleebs.dto.response.AuthenticationResponseRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para AuthenticationServiceImpl")
class AuthenticationServiceImplTest {
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private JwtService jwtService;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
        authenticationService = new AuthenticationServiceImpl(jwtService);
    }

    @Test
    @DisplayName("Deve autenticar um usuário corretamente e retornar os dados esperados")
    void testAuthenticate() {
        String mockToken = "mockedJwtToken";
        long expectedExpiry = 3600L;
        String expectedType = "Bearer";

        when(jwtService.generateToken(authentication)).thenReturn(mockToken);

        AuthenticationResponseRecord response = authenticationService.authenticate(authentication);

        assertEquals(mockToken, response.token());
        assertEquals(expectedType, response.type());
        assertEquals(expectedExpiry, response.expirationTime());

        verify(jwtService, times(1)).generateToken(authentication);
    }
}