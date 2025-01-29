package com.tech.teste.luiza.oracleebs.mapstruct;

import com.tech.teste.luiza.oracleebs.dto.request.LoggingRequestRecord;
import com.tech.teste.luiza.oracleebs.model.Logging;
import org.mapstruct.Mapper;

/**
 * Mapper para conversão entre o registro de requisição {@link LoggingRequestRecord}
 * e a entidade {@link Logging}.
 *
 * <p>Utiliza o MapStruct para realizar a transformação de objetos no contexto da aplicação.</p>
 *
 * <ul>
 *   <li>{@code toEntity}: Converte um {@link LoggingRequestRecord} para uma entidade {@link Logging}.</li>
 * </ul>
 *
 * <p>Configurado com o {@code componentModel = "spring"}.</p>
 *
 * @author Cicero Higor Gomes
 */
@Mapper(componentModel = "spring")
public interface LoggingMapper {
    Logging toEntity(LoggingRequestRecord loggingRequestRecord);
}
