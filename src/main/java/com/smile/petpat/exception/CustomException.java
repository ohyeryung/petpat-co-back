package com.smile.petpat.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private ExceptionMessage exceptionMessage;

    public CustomException(){
    }

    public CustomException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public CustomException(String message, ExceptionMessage exceptionMessage) {
        super(message);
        this.exceptionMessage = exceptionMessage;
    }

    public CustomException(String message, Throwable cause, ExceptionMessage exceptionMessage) {
        super(message, cause);
        this.exceptionMessage = exceptionMessage;
    }
}
