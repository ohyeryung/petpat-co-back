package com.smile.petpat.common.response;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.smile.petpat.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {

        ErrorResponse response = ErrorResponse.create()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<ErrorResponse> handleNullPointException(NullPointerException ex) {
        log.error("handleNullPointException : {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.create()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST);

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * @Vaild 검증 실패 시 에러 처리
     */
    @ExceptionHandler(value = {BindException.class})
    protected ResponseEntity<ErrorResponse> handleBindException(BindException ex) {
        log.error("handleBindException : {}", ex.getMessage());

        String message = ex.getMessage();
        String defaultMsg = message.substring(message.lastIndexOf("[")+1, message.lastIndexOf("]")); // "[" 또는 "]" 기준으로 메시지 추출

        ErrorResponse response = ErrorResponse.create()
                        .message(defaultMsg)
                        .httpStatus(HttpStatus.BAD_REQUEST);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        log.error("handleCustomException", ex);

        ErrorCode errorCode = ex.getErrorCode();
        String message = ex.getMessage();

        ErrorResponse response
                = ErrorResponse
                        .create()
                        .message(message)
                        .httpStatus(errorCode.getHttpStatus());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("handleException : {}", ex.getMessage());

        ErrorResponse response = ErrorResponse
                        .create()
                        .message(ex.getMessage())
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.badRequest().body(response);
    }
}
