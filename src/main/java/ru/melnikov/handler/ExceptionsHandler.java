package ru.melnikov.handler;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.melnikov.response.Error;
import ru.melnikov.response.ResponseData;

@ControllerAdvice
@Slf4j
class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<Object> illegalArgumentExceptions(IllegalArgumentException ex, WebRequest request) {
        log.error("illegalArgumentException", ex);

        ResponseData<String> error = new ResponseData<>();
        error.addError(new Error("-1","Server error",ex.getMessage()));

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleUncaughtExceptions(RuntimeException ex, WebRequest request) {
        log.error("Uncaught error", ex);

        ResponseData<String> error = new ResponseData<>();
        error.addError(new Error("-1","Server error","Server error"));

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
