package com.tech.teste.luiza.oracleebs.validator;

import com.tech.teste.luiza.oracleebs.validator.annotation.ValidDouble;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validador para a annotation {@link ValidDouble}.
 *
 * <p>Garante que um número do tipo {@link Double} possua, no máximo, duas casas decimais.</p>
 *
 * @author Cicero Higor Gomes
 */

public class DoubleDecimalValidator implements ConstraintValidator <ValidDouble, Double>{
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        String valueAsString = value.toString();
        return valueAsString.matches("^-?\\d+(\\.\\d{1,2})?$");
    }
}
