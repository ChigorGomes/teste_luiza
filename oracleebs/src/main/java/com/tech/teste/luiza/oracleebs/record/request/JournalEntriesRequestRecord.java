package com.tech.teste.luiza.oracleebs.dto.request;

import com.tech.teste.luiza.oracleebs.validator.annotation.ValidCurrency;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.Set;

public record JournalEntriesRequestRecord(@NotEmpty(message = "journal name cannot be null or empty") String journalName,
                                          LocalDate accountingDate,
                                          @ValidCurrency()
                                          @NotEmpty(message = "currencyCode name cannot be null or empty")
                                          String currencyCode ,
                                          @Valid Set<LinesRequestRecord> lines) {
}
