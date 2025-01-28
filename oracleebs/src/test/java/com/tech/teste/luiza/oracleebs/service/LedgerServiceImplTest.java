package com.tech.teste.luiza.oracleebs.service;

import com.tech.teste.luiza.oracleebs.dto.request.JournalEntriesRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.request.LedgerRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.request.LinesRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.response.JournalEntriesResponseRecord;
import com.tech.teste.luiza.oracleebs.dto.response.LedgerResponseRecord;
import com.tech.teste.luiza.oracleebs.dto.response.LinesResponseRecord;
import com.tech.teste.luiza.oracleebs.exception.NotFoundExceptionHandler;
import com.tech.teste.luiza.oracleebs.mapstruct.LedgerMapper;
import com.tech.teste.luiza.oracleebs.model.JournalEntries;
import com.tech.teste.luiza.oracleebs.model.JournalLines;
import com.tech.teste.luiza.oracleebs.model.Ledger;
import com.tech.teste.luiza.oracleebs.repository.LedgerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para LedgerServiceImpl")
class LedgerServiceImplTest {
    @Mock
    private LedgerRepository ledgerRepository;

    @InjectMocks
    private LedgerServiceImpl ledgerService;

    @Mock
    private LedgerMapper ledgerMapper;

    private Ledger ledger;
    private LedgerRequestRecord ledgerRequestRecord;
    private LedgerResponseRecord ledgerResponseRecord;

    @BeforeEach
    void setUp() {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                20.0,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "Ajuste de Despesas 2025",
                LocalDate.now(),
                "BRL",
                Set.of(linesRequestRecord)
        );

        ledgerRequestRecord = new LedgerRequestRecord(
                "Ajuste de Despesas",
                journalEntriesRequestRecord
        );

        ledger = new Ledger();
        ledger.setDescription("Ajuste de Despesas");
        ledger.setTranscationId(UUID.randomUUID());

        JournalLines journalLines = new JournalLines();
        journalLines.setAccountCode("CC1001");
        journalLines.setCredit(10.0);
        journalLines.setDebit(20.0);
        journalLines.setDescription("Despesa com material de escritório");

        JournalEntries journalEntries = new JournalEntries(
                "Ajuste de Despesas 2025",
                LocalDate.now(),
                "BRL",
                Set.of(journalLines)
        );
        ledger.setJournalEntries(journalEntries);

        LinesResponseRecord linesResponseRecord = new LinesResponseRecord(
                "CC1001",
                20.0,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesResponseRecord journalEntriesResponseRecord = new JournalEntriesResponseRecord(
                "Ajuste de Despesas 2025",
                LocalDate.now(),
                "BRL",
                Set.of(linesResponseRecord)
        );


        ledgerResponseRecord = new LedgerResponseRecord(
                ledger.getTranscationId(),
                ledger.getDescription(),
                journalEntriesResponseRecord
        );


    }

    @Test
    @DisplayName("Deve criar um ledger e retornar a resposta esperada")
    void testCreateLedger() {
        when(ledgerMapper.toEntity(ledgerRequestRecord)).thenReturn(ledger);
        when(ledgerRepository.save(ledger)).thenReturn(ledger);
        when(ledgerMapper.toResponseRecord(ledger)).thenReturn(ledgerResponseRecord);

        LedgerResponseRecord result = ledgerService.create(ledgerRequestRecord);
        assertNotNull(result);
        assertEquals(ledgerResponseRecord, result);

    }


    @Test
    @DisplayName("Deve encontrar um ledger pelo ID e retornar a resposta esperada")
    void testFindById() {
        when(ledgerRepository.findById(ledger.getTranscationId())).thenReturn(java.util.Optional.of(ledger));
        when(ledgerMapper.toResponseRecord(ledger)).thenReturn(ledgerResponseRecord);
        LedgerResponseRecord result = ledgerService.findById(ledger.getTranscationId());
        assertNotNull(result);
        assertEquals(ledgerResponseRecord, result);
    }


    @Test
    @DisplayName("Deve lançar uma exceção ao tentar encontrar um ledger inexistente pelo ID")
    void failuredFindById() {
        when(ledgerRepository.findById(ledger.getTranscationId())).thenReturn(java.util.Optional.empty());
        NotFoundExceptionHandler exception = assertThrows(NotFoundExceptionHandler.class, () -> {
            ledgerService.findById(ledger.getTranscationId());
        });
        assertEquals(ledger.getTranscationId() + " not found", exception.getMessage());

    }


}