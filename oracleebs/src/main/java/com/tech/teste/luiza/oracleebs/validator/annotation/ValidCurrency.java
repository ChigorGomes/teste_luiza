package com.tech.teste.luiza.oracleebs.validator.annotation;

import com.tech.teste.luiza.oracleebs.validator.CurrencyCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CurrencyCodeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrency {
    String message() default "CurrencyCode is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
