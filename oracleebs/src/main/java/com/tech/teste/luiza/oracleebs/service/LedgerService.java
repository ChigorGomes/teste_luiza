package com.tech.teste.luiza.oracleebs.service;


import com.tech.teste.luiza.oracleebs.dto.request.LedgerRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.response.LedgerResponseRecord;
import com.tech.teste.luiza.oracleebs.exception.NotFoundExceptionHandler;
import com.tech.teste.luiza.oracleebs.mapstruct.LedgerRequestMapper;
import com.tech.teste.luiza.oracleebs.repository.LedgerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LedgerService {
    private final LedgerRepository ledgerRepository;
    private final LedgerRequestMapper ledgerRequestMapper;

    public LedgerService(LedgerRepository ledgerRepository, LedgerRequestMapper ledgerRequestMapper) {
        this.ledgerRepository = ledgerRepository;
        this.ledgerRequestMapper = ledgerRequestMapper;
    }

    @Transactional
    public LedgerResponseRecord create(LedgerRequestRecord ledgerRequest) {
        var toEntity = ledgerRequestMapper.toEntity(ledgerRequest);
        return ledgerRequestMapper.toResponseRecord(ledgerRepository.save(toEntity));
    }

    @Transactional
    public LedgerResponseRecord findById(UUID id) {
        var entity = ledgerRepository.findById(id).orElseThrow(()-> new NotFoundExceptionHandler(id.toString()));
        return ledgerRequestMapper.toResponseRecord(entity);
    }

//    public LedgerRecord updateLines(UUID id, List<LinesRecord> linesRecord) {
//        var existingLedgerOpt = ledgerRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler(id.toString()));
//
//        if (existingLedgerOpt != null) {
//
////            // O Ledger já existe, então vamos atualizar apenas as linhas
////
////            // Verifique se o JournalEntries também existe
////            if (existingLedger.getJournalEntries() != null) {
//            JournalEntries existingJournalEntries = existingLedgerOpt.getJournalEntries();
////
//            var line =    ledgerRequestMapper.toJournalLines(linesRecord);
////                // Adicionar novas linhas ao JournalEntries
//            existingJournalEntries.getLines().add(line);
////
////                // Não altere os outros campos de JournalEntries, apenas adicione as novas linhas
//            existingJournalEntries.setLines(existingJournalEntries.getLines());
////
////                // Salve o Ledger com as novas linhas
//            return ledgerRequestMapper.toLedgerRequestRecord(ledgerRepository.save(existingLedgerOpt));
////
////
////            }
////
////        }
//
//
//        }
//        return null;
//    }

}

