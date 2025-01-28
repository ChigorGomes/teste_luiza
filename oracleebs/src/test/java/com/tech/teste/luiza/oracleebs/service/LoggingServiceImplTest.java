package com.tech.teste.luiza.oracleebs.service;

import com.tech.teste.luiza.oracleebs.dto.request.LoggingRequestRecord;
import com.tech.teste.luiza.oracleebs.mapstruct.LoggingMapper;
import com.tech.teste.luiza.oracleebs.model.Logging;
import com.tech.teste.luiza.oracleebs.repository.LogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para LoggingServiceImpl")
class LoggingServiceImplTest {
    @Mock
    private LogRepository logRepository;

    @InjectMocks
    private LoggingServiceImpl loggingService;


    @Mock
    private LoggingMapper loggingMapper;

     private Logging logging;

     LoggingRequestRecord loggingRequestRecord;

    @BeforeEach
    void setUp() {
        logging = new Logging();
        logging.setId(1L);
        logging.setMethod("POST");
        logging.setPath("/v1/ledger");
        logging.setStatus(200);
        logging.setPayload("{\"timestamp\":\"2025-01-27T19:31:00.186803406Z\",\"status\":404,\"error\":\"No static resource v1/authenticat.\",\"path\":\"/v1/authenticat\",\"method\":\"POST\"}");


        loggingRequestRecord = new LoggingRequestRecord(
          200,
                "{\"timestamp\":\"2025-01-27T19:31:00.186803406Z\",\"status\":404,\"error\":\"No static resource v1/authenticat.\",\"path\":\"/v1/authenticat\",\"method\":\"POST\"}",
        "/v1/ledger",
                "POST"
        );
    }


    @Test
    @DisplayName("Deve salvar o log corretamente e retornar verdadeiro")
    void testSave() throws IOException {
        Logging mock = new Logging();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/v1/ledger");
        request.setMethod("POST");

        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(HttpStatus.OK.value());

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        response.getWriter().write(logging.getPayload());
        responseWrapper.copyBodyToResponse();

        when(loggingMapper.toEntity(any(LoggingRequestRecord.class))).thenReturn(mock);

        boolean result = loggingService.createLog(request, response, responseWrapper);
        assertTrue(result);
        verify(loggingMapper, times(1)).toEntity(any(LoggingRequestRecord.class));
        verify(logRepository, times(1)).save(mock);

    }


}