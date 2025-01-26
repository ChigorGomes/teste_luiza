package com.tech.teste.luiza.oracleebs.mapstruct;

import com.tech.teste.luiza.oracleebs.dto.request.LedgerRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.response.LedgerResponseRecord;
import com.tech.teste.luiza.oracleebs.model.Ledger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LedgerMapper {
    Ledger toEntity(LedgerRequestRecord ledgerRequestRecord);
    LedgerResponseRecord toResponseRecord(Ledger ledger);
}
