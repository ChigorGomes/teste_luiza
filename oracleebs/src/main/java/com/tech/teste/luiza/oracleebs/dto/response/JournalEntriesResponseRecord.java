package com.tech.teste.luiza.oracleebs.dto.response;

import java.time.LocalDate;
import java.util.Set;

public record JournalEntriesResponseRecord(String journalName, LocalDate accountingDate, String currencyCode ,
                                           Set<LinesResponseRecord> lines) {
}
