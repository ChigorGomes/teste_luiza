package com.tech.teste.luiza.oracleebs.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpResponseProcessor {

    public static JsonNode convertToJsonNodeResponseBody(ContentCachingResponseWrapper responseWrapper) throws IOException {
        byte[] contentAsByteArray = responseWrapper.getContentAsByteArray();
        String content = new String(contentAsByteArray, responseWrapper.getCharacterEncoding());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(content);
    }

    public static String convertToStringResponseBody(ContentCachingResponseWrapper responseWrapper) throws UnsupportedEncodingException {
        byte[] contentAsByteArray = responseWrapper.getContentAsByteArray();
        return new String(contentAsByteArray, responseWrapper.getCharacterEncoding());
    }
}
