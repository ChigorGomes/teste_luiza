package com.tech.teste.luiza.oracleebs.dto.request;

import com.tech.teste.luiza.oracleebs.validator.annotation.ValidDouble;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

public record LinesRequestRecord(@NotEmpty(message = "accountCode name cannot be null or empty")
                                 String accountCode,
                                 @ValidDouble()
                                 @DecimalMin(value = "0.01", message = "debit must be greater than zero.")
                                 Double debit,
                                 @DecimalMin(value = "0.01", message = "credit must be greater than zero.")
                                 Double credit,
                                 @NotEmpty(message = "costCenter name cannot be null or empty")
                                 String costCenter,
                                 @NotEmpty(message = "description name cannot be null or empty")
                                 String description) {
}
