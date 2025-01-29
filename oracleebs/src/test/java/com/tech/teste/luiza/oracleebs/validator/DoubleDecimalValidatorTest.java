package com.tech.teste.luiza.oracleebs.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para DoubleDecimalValidator")
class DoubleDecimalValidatorTest {
    private DoubleDecimalValidator doubleDecimalValidator;
    @BeforeEach
    void setUp() {
        doubleDecimalValidator = new DoubleDecimalValidator();
    }
    @Test
    @DisplayName("Deve retornar verdadeiro para um valor decimal válido")
    void testValidDoubleDecimal(){
        assertTrue(doubleDecimalValidator.isValid(1.0,null));
    }


    @Test
    @DisplayName("Deve retornar falso para um valor decimal inválido com mais de duas casas decimais")
    void testInvalidDoubleDecimal(){
        assertFalse(doubleDecimalValidator.isValid(17.789,null));
    }



}