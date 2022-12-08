package com.smile.petpat.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.InvalidClassException;
import java.security.InvalidParameterException;

@RestControllerAdvice
@Slf4j
public class RestApiExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("handleHttpRequestMethodNotSupportedException", ex);

        final ErrorResponse response
                = ErrorResponse
                        .create()
                        .httpStatus(HttpStatus.METHOD_NOT_ALLOWED)
                        .message(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

//    @ExceptionHandler({IllegalArgumentException.class, IOException.class})
//    public ResponseEntity<ErrorResponse> handleApiRequestException(Exception ex) {
//        return exceptionHandle(ex);
//    }
//
//    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<ErrorResponse> handleValidationException(Exception ex) {
//        return exceptionHandle(ex);
//    }

    // @Valid 검증 실패 시 Catch
//    @ExceptionHandler(InvalidParameterException.class)
//    protected ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException ex) {
//        log.error("handleInvalidParameterException", ex);
//
//        ErrorCode errorCode = ex.get;
//
//        ErrorResponse response
//                = ErrorResponse
//                        .create()
//                        .httpStatus(errorCode.getHttpStatus())
//                        .message(ex.toString())
//                        .errors(ex.getE)
//    }
}
