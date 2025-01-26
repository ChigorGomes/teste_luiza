package com.tech.teste.luiza.oracleebs.controller;

import com.tech.teste.luiza.oracleebs.dto.request.LedgerRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.response.LedgerResponseRecord;
import com.tech.teste.luiza.oracleebs.service.LedgerService;
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

@RestController
@RequestMapping("/v1/ledger")
public class LedgerController {
    private final LedgerService ledgerService;

    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }


    @PostMapping("/create")
    public ResponseEntity<LedgerResponseRecord> createLedger(@Valid @RequestBody LedgerRequestRecord ledger) {
        var entity = ledgerService.create(ledger);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LedgerResponseRecord> getLedger(@PathVariable UUID id) {
        var entity = ledgerService.findById(id);
        return ResponseEntity.ok().body(entity);
    }


}
