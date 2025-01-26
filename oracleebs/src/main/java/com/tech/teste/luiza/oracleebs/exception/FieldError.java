package com.tech.teste.luiza.oracleebs.exception;

import java.time.LocalDateTime;
import java.util.List;


public record FieldError(LocalDateTime timestamp, Integer status, String error, String path, String method,List<String> fields) {

    public FieldError(CustomError customError, List<String> fields){
        this(customError.timestamp(),customError.status(),customError.error(),customError.path(),customError.method(),fields);
    }

}
