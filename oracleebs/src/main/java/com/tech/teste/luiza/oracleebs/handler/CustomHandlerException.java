package com.tech.teste.luiza.oracleebs.handler;

import com.tech.teste.luiza.oracleebs.exception.CustomError;
import com.tech.teste.luiza.oracleebs.exception.FieldError;
import com.tech.teste.luiza.oracleebs.exception.NotFoundExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;
/**
 * Manipulador global de exceções para a aplicação.
 *
 * <p>Esta classe utiliza a anotação {@code @ControllerAdvice} para capturar e tratar diferentes
 * tipos de exceções, proporcionando respostas detalhadas aos erros em formato consistente.</p>
 *
 * <p>Inclui tratadores específicos para exceções como:
 * <ul>
 *   <li>{@link NotFoundExceptionHandler}</li>
 *   <li>{@link MethodArgumentNotValidException}</li>
 *   <li>{@link IllegalArgumentException}</li>
 *   <li>{@link HttpRequestMethodNotSupportedException}</li>
 *   <li>{@link NoResourceFoundException}</li>
 *   <li>Outras exceções genéricas.</li>
 * </ul>
 * </p>
 *
 * @author Cicero Higor Gomes
 */
@ControllerAdvice
public class CustomHandlerException {
    @ExceptionHandler(NotFoundExceptionHandler.class)
    ResponseEntity<CustomError> handleNotFoundException(NotFoundExceptionHandler handler, HttpServletRequest request) {
        CustomError customError = new CustomError(
                Instant.now(),
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

        CustomError customError = new CustomError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                request.getRequestURI(),
                request.getMethod()
        );

        FieldError fieldError = new FieldError(customError, error);

        return new ResponseEntity<>(fieldError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {IllegalArgumentException.class})
    public final ResponseEntity<CustomError> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        CustomError customError = new CustomError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI(),
                request.getMethod()
        );

        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    ResponseEntity<CustomError> handleMethodHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        CustomError customError = new CustomError(
                Instant.now(),
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                ex.getMessage(),
                request.getRequestURI(),
                request.getMethod()
        );


        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(customError);
    }

    @ExceptionHandler(value = {NoResourceFoundException.class})
    public final ResponseEntity<CustomError> handleNoResourceFoundException(NoResourceFoundException ex, HttpServletRequest request) {
        CustomError customError = new CustomError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI(),
                request.getMethod()
        );

        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<CustomError> handleAllExceptions(Exception ex, HttpServletRequest request) {
        CustomError customError = new CustomError(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getRequestURI(),
                request.getMethod()
        );

        return new ResponseEntity<>(customError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
