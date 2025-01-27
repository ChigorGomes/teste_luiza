package com.tech.teste.luiza.oracleebs.exception;

import java.time.Instant;
import java.util.List;


public record FieldError(Instant timestamp, Integer status, String error, String path, String method, List<String> fields) {

    public FieldError(CustomError customError, List<String> fields){
        this(customError.timestamp(),customError.status(),customError.error(),customError.path(),customError.method(),fields);
    }

}
