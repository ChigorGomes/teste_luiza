package com.tech.teste.luiza.oracleebs.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record LedgerRequestRecord(@NotEmpty(message = "description cannot be null or empty") String description,
                                  @Valid JournalEntriesRequestRecord journalEntries) {



}
