package com.tech.teste.luiza.oracleebs.handler;

import com.tech.teste.luiza.oracleebs.exception.CustomError;
import com.tech.teste.luiza.oracleebs.exception.FieldError;
import com.tech.teste.luiza.oracleebs.exception.NotFoundExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class CustomHandlerException {
    @ExceptionHandler(NotFoundExceptionHandler.class)
    ResponseEntity<CustomError> handleNotFoundException(NotFoundExceptionHandler handler, HttpServletRequest request) {
        CustomError customError= new CustomError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                handler.getMessage(),
                request.getRequestURI(),
                request.getMethod()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<FieldError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        var error = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        CustomError customError= new CustomError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Validation error",
                request.getRequestURI(),
                request.getMethod()
        );

        FieldError fieldError = new FieldError(customError, error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldError);


    }

    @ExceptionHandler(value = { Exception.class })
    public final ResponseEntity<CustomError> handleAllExceptions(Exception ex,  HttpServletRequest request) {
        CustomError customError= new CustomError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI(),
                request.getMethod()
        );

        return new ResponseEntity<>(customError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
