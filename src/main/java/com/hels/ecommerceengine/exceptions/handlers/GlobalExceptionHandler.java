package com.hels.ecommerceengine.exceptions.handlers;

import com.hels.ecommerceengine.exceptions.ApiException;
import com.hels.ecommerceengine.exceptions.UnauthorizedException;
import com.hels.ecommerceengine.exceptions.responses.StandardError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Iterator;
import java.util.stream.Stream;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ValidationError handle(BindException ex) {
        log(ex, 1);
        ValidationError validationError = new ValidationError();
        ex.getBindingResult().getAllErrors().forEach(error ->
                validationError.addError(((FieldError) error).getField(), error.getDefaultMessage()));
        return validationError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApiException.class)
    public StandardError handle(ApiException ex) {
        log(ex, 10);
        return new StandardError(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public StandardError handle(UnauthorizedException ex){
        log(ex, 10);
        return new StandardError("Unauthorized");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public StandardError handle(HttpRequestMethodNotSupportedException ex) {
        log(ex, 10);
        return new StandardError(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public StandardError handle(HttpMessageNotReadableException ex) {
        log(ex, 1);
        return new StandardError("Requisição inválida");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public StandardError handle(RuntimeException ex) {
        log(ex, 10);
        return new StandardError("An internal error has occurred");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public StandardError handle(Exception ex) {
        log(ex, 10);
        return new StandardError("An internal error has occurred");
    }

    private void log(Exception ex, int stackNumbers) {
        log.error("Message: {}", ex.getMessage());
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<StackTraceElement> elementIterator = Stream.of(ex.getStackTrace()).iterator();
        for (int i = 0; i < stackNumbers && elementIterator.hasNext(); i++)
            stringBuilder.append(elementIterator.next()).append("\n");
        log.info("Error trace: {}", stringBuilder);
    }
}
