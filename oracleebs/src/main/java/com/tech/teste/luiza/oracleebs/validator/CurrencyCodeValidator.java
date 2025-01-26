package com.tech.teste.luiza.oracleebs.validator;

import com.tech.teste.luiza.oracleebs.validator.annotation.ValidCurrency;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Currency;

public class CurrencyCodeValidator implements ConstraintValidator<ValidCurrency, String> {

    @Override
    public boolean isValid(String codigo, ConstraintValidatorContext constraintValidatorContext) {
        if (codigo == null) {
            return true;
        }
        try {
            Currency moeda = Currency.getInstance(codigo.toUpperCase());
            return moeda!= null;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
