package com.tech.teste.luiza.oracleebs.dto.response;

import java.util.UUID;

public record LedgerResponseRecord(UUID transcationId, String description, JournalEntriesResponseRecord journalEntries) {



}
