package com.tech.teste.luiza.oracleebs.handler;

import com.tech.teste.luiza.oracleebs.exception.CustomError;
import com.tech.teste.luiza.oracleebs.exception.FieldError;
import com.tech.teste.luiza.oracleebs.exception.NotFoundExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Classe de testes para o {@link CustomHandlerException}.
 *
 * <p>Valida o comportamento dos métodos de manipulação de exceções,
 * garantindo o retorno correto de erros personalizados para diferentes
 * tipos de exceções lançadas na aplicação.</p>
 *
 * <ul>
 *     <li>Testa manipulação de erros como {@code NotFoundExceptionHandler}.</li>
 *     <li>Valida exceções genéricas e específicas como argumentos inválidos, métodos não suportados, etc.</li>
 *     <li>Garante a construção correta das respostas associadas às requisições (URI, método e status).</li>
 * </ul>
 *
 * @author Cicero Higor Gomes
 */

@DisplayName("Testes para CustomHandlerException")
class CustomHandlerExceptionTest {

    private CustomHandlerException customHandlerException;
    private HttpServletRequest mockRequest;

    @BeforeEach
    void setUp() {
        customHandlerException = new CustomHandlerException();
        mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURI()).thenReturn("/v1/ledger");
    }

    @Test
    @DisplayName("Deve retornar erro 404 para NotFoundExceptionHandler")
    void testHandleNotFoundException() {
        NotFoundExceptionHandler exception = new NotFoundExceptionHandler("Id");
        when(mockRequest.getMethod()).thenReturn("GET");

        ResponseEntity<CustomError> response = customHandlerException.handleNotFoundException(exception, mockRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Id not found", response.getBody().error());
        assertEquals("/v1/ledger", response.getBody().path());
        assertEquals("GET", response.getBody().method());
    }

    @Test
    @DisplayName("Deve retornar erro 400 para MethodArgumentNotValidException")
    void testHandleMethodArgumentNotValidException() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(mockRequest.getMethod()).thenReturn("POST");
        when(exception.getBindingResult()).thenReturn(mock(org.springframework.validation.BindingResult.class));
        ResponseEntity<FieldError> response = customHandlerException.handleMethodArgumentNotValidException(exception, mockRequest);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Validation error", response.getBody().error());
        assertEquals("/v1/ledger", response.getBody().path());
        assertEquals("POST", response.getBody().method());
    }

    @Test
    @DisplayName("Deve retornar erro 400 para IllegalArgumentException")
    void testHandleIllegalArgumentException() {
        when(mockRequest.getMethod()).thenReturn("POST");
        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");
        ResponseEntity<CustomError> response = customHandlerException.handleIllegalArgumentException(exception, mockRequest);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Invalid argument", response.getBody().error());
        assertEquals("/v1/ledger", response.getBody().path());
        assertEquals("POST", response.getBody().method());
    }

    @Test
    @DisplayName("Deve retornar erro 405 para HttpRequestMethodNotSupportedException")
    void testHandleHttpRequestMethodNotSupportedException() {
        HttpRequestMethodNotSupportedException exception = new HttpRequestMethodNotSupportedException("POST");
        when(mockRequest.getMethod()).thenReturn("GET");

        ResponseEntity<CustomError> response = customHandlerException.handleMethodHttpRequestMethodNotSupportedException(exception, mockRequest);
        assertNotNull(response);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Request method 'POST' is not supported", response.getBody().error());
        assertEquals("/v1/ledger", response.getBody().path());
        assertEquals("GET", response.getBody().method());
    }

    @Test
    @DisplayName("Deve retornar erro 404 para NoResourceFoundException")
    void testHandleNoResourceFoundException() {
        NoResourceFoundException exception = new NoResourceFoundException(HttpMethod.GET,"");

        ResponseEntity<CustomError> response = customHandlerException.handleNoResourceFoundException(exception, mockRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("No static resource .", response.getBody().error());
    }

    @Test
    @DisplayName("Deve retornar erro 500 para Exception genérica")
    void testHandleAllExceptions() {
        Exception exception = new Exception("Internal server error");
        when(mockRequest.getMethod()).thenReturn("GET");

        ResponseEntity<CustomError> response = customHandlerException.handleAllExceptions(exception, mockRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Internal server error", response.getBody().error());
        assertEquals("/v1/ledger", response.getBody().path());
        assertEquals("GET", response.getBody().method());
    }
}