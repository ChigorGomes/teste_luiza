package com.tech.teste.luiza.oracleebs.mapstruct;

import com.tech.teste.luiza.oracleebs.dto.request.LoggingRequestRecord;
import com.tech.teste.luiza.oracleebs.model.Logging;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoggingMapper {
    Logging toEntity(LoggingRequestRecord loggingRequestRecord);
}
