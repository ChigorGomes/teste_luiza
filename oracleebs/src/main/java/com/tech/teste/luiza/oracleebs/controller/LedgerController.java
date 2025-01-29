package com.tech.teste.luiza.oracleebs.controller;

import com.tech.teste.luiza.oracleebs.dto.request.LedgerRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.response.LedgerResponseRecord;
import com.tech.teste.luiza.oracleebs.service.LedgerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
/**
 * Controlador responsável por gerenciar operações relacionadas a Ledgers.
 *
 * <p>Define endpoints para criação de um novo Ledger e recuperação de um Ledger
 * por ID, utilizando o serviço {@link LedgerServiceImpl}.</p>
 *
 * @author Cicero Higor Gomes
 */
@RestController
@RequestMapping("/v1/ledger")
public class LedgerController {
    private final LedgerServiceImpl ledgerServiceImpl;

    public LedgerController(LedgerServiceImpl ledgerServiceImpl) {
        this.ledgerServiceImpl = ledgerServiceImpl;
    }


    @PostMapping()
    public ResponseEntity<LedgerResponseRecord> createLedger(@Valid @RequestBody LedgerRequestRecord ledger) {
        var entity = ledgerServiceImpl.create(ledger);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LedgerResponseRecord> getLedger(@PathVariable UUID id) {
        var entity = ledgerServiceImpl.findById(id);
        return ResponseEntity.ok().body(entity);
    }


}
