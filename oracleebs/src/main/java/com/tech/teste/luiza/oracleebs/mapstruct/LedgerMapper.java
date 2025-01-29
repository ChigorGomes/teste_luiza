package com.tech.teste.luiza.oracleebs.mapstruct;

import com.tech.teste.luiza.oracleebs.dto.request.LedgerRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.response.LedgerResponseRecord;
import com.tech.teste.luiza.oracleebs.model.Ledger;
import org.mapstruct.Mapper;
/**
 * Mapper para conversão entre as entidades {@code Ledger}, registros de requisição
 * ({@link LedgerRequestRecord}) e registros de resposta ({@link LedgerResponseRecord}).
 *
 * <p>Utiliza o MapStruct para facilitar a transformação de objetos no contexto da aplicação.</p>
 *
 * <ul>
 *   <li>{@code toEntity}: Converte um {@link LedgerRequestRecord} para uma entidade {@link Ledger}.</li>
 *   <li>{@code toResponseRecord}: Converte uma entidade {@link Ledger} para um {@link LedgerResponseRecord}.</li>
 * </ul>
 *
 * <p>Configurado com o {@code componentModel = "spring"}.</p>
 *
 * @author Cicero Higor Gomes
 */
@Mapper(componentModel = "spring")
public interface LedgerMapper {
    Ledger toEntity(LedgerRequestRecord ledgerRequestRecord);
    LedgerResponseRecord toResponseRecord(Ledger ledger);
}
