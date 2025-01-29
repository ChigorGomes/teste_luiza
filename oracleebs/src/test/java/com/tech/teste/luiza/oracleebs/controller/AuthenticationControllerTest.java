package com.tech.teste.luiza.oracleebs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.teste.luiza.oracleebs.config.filter.RequestLoggingFilter;
import com.tech.teste.luiza.oracleebs.dto.response.AuthenticationResponseRecord;
import com.tech.teste.luiza.oracleebs.service.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
@DisplayName("Testes para AuthenticationController")
class AuthenticationControllerTest {


    @InjectMocks
    @Autowired
    private AuthenticationController authenticationController;

    @MockitoBean
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private Authentication authentication;


    @MockitoBean
    private RequestLoggingFilter requestLoggingFilter;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        authenticationController = new AuthenticationController(authenticationService);
    }


    @Test
    @DisplayName("Deve autenticar um usuário e retornar um AuthenticationResponseRecord")
    @WithMockUser(username = "testUser", roles = {"USER"}) // Simula um usuário autenticado
    void testAuthenticate() throws Exception {
        String mockToken = "mockedJwtToken";
        long expectedExpiry = 3600L;
        String expectedType = "Bearer";
        AuthenticationResponseRecord mockResponse = new AuthenticationResponseRecord(mockToken, expectedType, expectedExpiry);
        when(authenticationService.authenticate(any(Authentication.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/v1/ledger/authenticate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").value(mockToken))
                .andExpect(jsonPath("$.type").value(expectedType))
                .andExpect(jsonPath("$.expirationTime").value(expectedExpiry))
                .andDo(print());

    }

    @Test
    @DisplayName("Deve retornar 401 ao falhar na autenticação")
    void testAuthenticateFailure() throws Exception {
        String invalidToken = "invalidJwtToken";
        when(authenticationService.authenticate(any(Authentication.class))).thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro de autenticação"));
        mockMvc.perform(post("/v1/ledger/authenticate")
                        .header("Authorization", "Bearer " + invalidToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }







}