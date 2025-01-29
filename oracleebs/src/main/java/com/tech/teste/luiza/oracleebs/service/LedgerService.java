package com.tech.teste.luiza.oracleebs.service;

import com.tech.teste.luiza.oracleebs.dto.request.LedgerRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.response.LedgerResponseRecord;

import java.util.UUID;
/**
 * Serviço responsável pelas operações relacionadas a Ledgers.
 *
 * <p>Define métodos para criação e consulta de Ledgers por ID.</p>
 *
 * @author Cicero Higor Gomes
 */

public interface LedgerService {
    LedgerResponseRecord create(LedgerRequestRecord ledgerRequest);
    LedgerResponseRecord findById(UUID id);
}
