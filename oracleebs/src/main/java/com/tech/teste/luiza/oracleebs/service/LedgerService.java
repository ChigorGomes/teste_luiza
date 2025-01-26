package com.tech.teste.luiza.oracleebs.service;

import com.tech.teste.luiza.oracleebs.dto.request.LedgerRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.response.LedgerResponseRecord;

import java.util.UUID;

public interface LedgerService {
    LedgerResponseRecord create(LedgerRequestRecord ledgerRequest);
    LedgerResponseRecord findById(UUID id);
}
