package com.smile.petpat.common.response;

import com.smile.petpat.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<ErrorResponse> handleInvalidParameterException(MethodArgumentNotValidException ex) {
//        log.error("handleMethodArgumentNotValidException", ex);
//
//        ErrorCode errorCode = ex.getErrorCode();
//
//        ErrorResponse response
//                = ErrorResponse
//                        .create()
//                        .httpStatus(errorCode.getHttpStatus())
//                        .message(ex.getBinding)
//                        .errors(ex.getE);
//
//        return new ResponseEntity<>(response, errorCode.getHttpStatus());
//    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        log.error("handleAllException", ex);

        ErrorCode errorCode = ex.getErrorCode();

        ErrorResponse response
                = ErrorResponse
                        .create()
                        .httpStatus(errorCode.getHttpStatus())
                        .message(ex.toString());

        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("handleException", ex);

        ErrorResponse response
                = ErrorResponse
                        .create()
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(ex.toString());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
