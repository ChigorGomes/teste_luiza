package com.tech.teste.luiza.oracleebs.service;

import com.tech.teste.luiza.oracleebs.dto.request.LoggingRequestRecord;
import com.tech.teste.luiza.oracleebs.mapstruct.LoggingMapper;
import com.tech.teste.luiza.oracleebs.repository.LogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;

import static com.tech.teste.luiza.oracleebs.util.HttpResponseProcessor.convertToStringResponseBody;

@Service
public class LoggingServiceImpl implements LoggingService {
    private final LogRepository logRepository;
    private final LoggingMapper loggingMapper;

    public LoggingServiceImpl(LogRepository logRepository, LoggingMapper loggingMapper) {
        this.logRepository = logRepository;
        this.loggingMapper = loggingMapper;
    }

    @Override
    @Transactional
    public boolean createLog(HttpServletRequest request, HttpServletResponse response, ContentCachingResponseWrapper responseWrapper) throws UnsupportedEncodingException {

        LoggingRequestRecord requestRecord = new LoggingRequestRecord(
                response.getStatus(),
                convertToStringResponseBody(responseWrapper),
                request.getRequestURI(),
                request.getMethod()
        );

        var entity = loggingMapper.toEntity(requestRecord);
        logRepository.save(entity);

        return true;
    }


}
