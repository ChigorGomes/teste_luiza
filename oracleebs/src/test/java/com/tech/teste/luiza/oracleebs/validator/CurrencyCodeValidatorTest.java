package com.tech.teste.luiza.oracleebs.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de testes para o {@link CurrencyCodeValidator}.
 *
 * <p>Valida os casos de uso para verificar a conformidade de códigos de moeda,
 * incluindo códigos válidos, inválidos, vazios e nulos.</p>
 *
 * @author Cicero Higor Gomes
 */

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para CurrencyCodeValidator")
class CurrencyCodeValidatorTest {
    private CurrencyCodeValidator currencyCodeValidator;

    @BeforeEach
    void setUp() {
        currencyCodeValidator = new CurrencyCodeValidator();
    }
    @Test
    @DisplayName("Deve retornar verdadeiro para um código de moeda válido em maiúsculas (ex.: BRL)")
    void testValidCurrency(){
        assertTrue(currencyCodeValidator.isValid("BRL", null));
    }

    @Test
    @DisplayName("Deve retornar verdadeiro para um código de moeda válido em minúsculas (ex.: usd)")
    void testValidCurrencyToLowerCase(){
        assertTrue(currencyCodeValidator.isValid("usd", null));
    }

    @Test
    @DisplayName("Deve retornar falso para um código de moeda inválido (ex.: ABC)")
    void testeInvalidCurrency(){
        assertFalse(currencyCodeValidator.isValid("ABC", null));
    }

    @Test
    @DisplayName("Deve retornar falso para um código de moeda vazio")
    void testeInvalidCurrencyEmpty(){
        assertFalse(currencyCodeValidator.isValid("", null));
    }

    @Test
    @DisplayName("Deve retornar falso para um código de moeda nulo")
    void testeInvalidCurrencyNull(){
        assertFalse(currencyCodeValidator.isValid(null, null));
    }




}