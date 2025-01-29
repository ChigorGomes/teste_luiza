package com.tech.teste.luiza.oracleebs.controller;

import com.tech.teste.luiza.oracleebs.dto.response.AuthenticationResponseRecord;
import com.tech.teste.luiza.oracleebs.service.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

@DisplayName("Testes para AuthenticationController")
class AuthenticationControllerTest {
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationController = new AuthenticationController(authenticationService);
    }

    @Test
    @DisplayName("Deve autenticar um usuário e retornar um AuthenticationResponseRecord")
    void testAuthenticate() {
        String mockToken = "mockedJwtToken";
        long expectedExpiry = 3600L;
        String expectedType = "Bearer";
        AuthenticationResponseRecord mockResponse = new AuthenticationResponseRecord(mockToken, expectedType, expectedExpiry);

        when(authenticationService.authenticate(authentication)).thenReturn(mockResponse);

        ResponseEntity<AuthenticationResponseRecord> response = authenticationController.authenticate(authentication);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockResponse, response.getBody());

        verify(authenticationService, times(1)).authenticate(authentication);
    }

    @Test
    @DisplayName("Deve lançar erro ao falhar na autenticação")
    void testAuthenticateWithError() {
        when(authenticationService.authenticate(authentication))
                .thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro de autenticação"));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                authenticationController.authenticate(authentication)
        );
        assertNotNull(exception);
        assertEquals(401, exception.getStatusCode().value());
        verify(authenticationService, times(1)).authenticate(authentication);
    }

}