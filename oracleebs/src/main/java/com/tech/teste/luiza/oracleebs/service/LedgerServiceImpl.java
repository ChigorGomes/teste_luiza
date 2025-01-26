package com.tech.teste.luiza.oracleebs.service;


import com.tech.teste.luiza.oracleebs.dto.request.LedgerRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.response.LedgerResponseRecord;
import com.tech.teste.luiza.oracleebs.exception.NotFoundExceptionHandler;
import com.tech.teste.luiza.oracleebs.mapstruct.LedgerMapper;
import com.tech.teste.luiza.oracleebs.repository.LedgerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LedgerServiceImpl implements LedgerService {
    private final LedgerRepository ledgerRepository;
    private final LedgerMapper ledgerMapper;

    public LedgerServiceImpl(LedgerRepository ledgerRepository, LedgerMapper ledgerMapper) {
        this.ledgerRepository = ledgerRepository;
        this.ledgerMapper = ledgerMapper;
    }

    @Override
    @Transactional
    public LedgerResponseRecord create(LedgerRequestRecord ledgerRequest) {
        var toEntity = ledgerMapper.toEntity(ledgerRequest);
        return ledgerMapper.toResponseRecord(ledgerRepository.save(toEntity));
    }

    @Override
    @Transactional
    public LedgerResponseRecord findById(UUID id) {
        var entity = ledgerRepository.findById(id).orElseThrow(()-> new NotFoundExceptionHandler(id.toString()));
        return ledgerMapper.toResponseRecord(entity);
    }


}

