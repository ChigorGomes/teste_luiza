package com.tech.teste.luiza.oracleebs.validator.annotation;

import com.tech.teste.luiza.oracleebs.validator.CurrencyCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation personalizada para validação de códigos de moeda.
 *
 * <p>Valida se o código de moeda fornecido é válido, utilizando a lógica definida no validador {@link CurrencyCodeValidator}.</p>
 *
 * @author Cicero Higor Gomes
 */

@Constraint(validatedBy = CurrencyCodeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrency {
    String message() default "CurrencyCode is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
