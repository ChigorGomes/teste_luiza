package com.tech.teste.luiza.oracleebs.validator;

import com.tech.teste.luiza.oracleebs.validator.annotation.ValidDouble;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DoubleDecimalValidator implements ConstraintValidator <ValidDouble, Double>{
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value <= 0.0) {
            return true;
        }
        String valueAsString = value.toString();
        return valueAsString.matches("^\\d+(\\.\\d{1,2})?$");
    }
}
