package com.tech.teste.luiza.oracleebs.validator.annotation;

import com.tech.teste.luiza.oracleebs.validator.DoubleDecimalValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DoubleDecimalValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDouble {
    String message() default "The number must have at most two decimal places.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}