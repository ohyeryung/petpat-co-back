package com.smile.petpat.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestControllerAdvice
public class RestApiExceptionHandler {

    public ResponseEntity<ErrorResponse> exceptionHandle(Exception ex) {
        ErrorResponse message = new ErrorResponse();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        message.setMsg(ex.getMessage());

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(message);
    }

    @ExceptionHandler({IllegalArgumentException.class, IOException.class})
    public ResponseEntity<ErrorResponse> handleApiRequestException(Exception ex) {
        return exceptionHandle(ex);
    }
}
