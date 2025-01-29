package com.tech.teste.luiza.oracleebs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Classe de testes para o {@link JwtServiceImpl}.
 *
 * <p>Valida a geração de tokens JWT, garantindo que o token seja gerado corretamente
 * com base nas informações fornecidas.</p>
 *
 * @author Cicero Higor Gomes
 */

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para JwtServiceImpl")
class JwtServiceImplTest {
    private JwtServiceImpl jwtService;

    @Mock
    private JwtEncoder jwtEncoder;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
        jwtService = new JwtServiceImpl(jwtEncoder);
    }

    @Test
    @DisplayName("Deve gerar um token JWT corretamente com as informações fornecidas")
    void testGenerateToken() {
        // Mock do comportamento do Authentication
        when(authentication.getName()).thenReturn("testUser");

        // Mock do retorno do JwtEncoder
        String expectedToken = "mockedTokenValue";
        Jwt mockJwt = mock(Jwt.class);
        when(mockJwt.getTokenValue()).thenReturn(expectedToken);
        when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(mockJwt);

        // Executa o método do serviço
        String token = jwtService.generateToken(authentication);

        // Verifica se o retorno é o esperado
        assertEquals(expectedToken, token);

        // Verifica se o JwtEncoder foi chamado corretamente
        verify(jwtEncoder, times(1)).encode(any(JwtEncoderParameters.class));
    }
}