package com.tech.teste.luiza.oracleebs.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de testes para o {@link DoubleDecimalValidator}.
 *
 * <p>Verifica a validação de valores decimais, incluindo casos válidos, inválidos, nulos
 * e números negativos.</p>
 *
 * @author Cicero Higor Gomes
 */

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

    @Test
    @DisplayName("Deve retornar falso para quando enviar null")
    void testInvalidNull(){
        assertFalse(doubleDecimalValidator.isValid(null,null));
    }

    @Test
    @DisplayName("Deve retornar true para quando enviar um double com uma casa decimal")
    void testValidDecimal(){
        assertTrue(doubleDecimalValidator.isValid(10.0,null));
    }

    @Test
    @DisplayName("Deve retornar true para quando enviar um double negativo")
    void testInvalidNegativeDecimal(){
        assertTrue(doubleDecimalValidator.isValid(-10.0,null));
    }

    @Test
    @DisplayName("Deve retornar true para quando enviar um double negativo com uma casa decimal")
    void testInvalidNegativeDoubleDecimal(){
        assertFalse(doubleDecimalValidator.isValid(-10.456,null));
    }



}