package com.smile.petpat.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    private LocalDateTime timestamp = LocalDateTime.now();
    public String message; // 예외 메세지
    public HttpStatus httpStatus; // Http 상태 값 400, 404, 500 등

    // @Valid 검증 안된 필드가 담김
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("errors")
    public List<CustomFieldError> customFieldErrors;

    public ErrorResponse() {}

    public ErrorResponse(HttpStatus status, String message) {
        this.httpStatus = status;
        this.message = message;
    }

    static public ErrorResponse create() {
        return new ErrorResponse();
    }

    public ErrorResponse message(String message) {
        this.message = message;
        return this;
    }

    public ErrorResponse httpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }
    public ErrorResponse errors(Errors errors) {
        setCustomFieldErrors(errors.getFieldErrors());
        return this;
    }

    public List<CustomFieldError> getCustomFieldErrors() {
        return customFieldErrors;
    }

    //BindingResult.getFieldErrors() 메소드를 통해 전달받은 fieldErrors
    public void setCustomFieldErrors(List<FieldError> fieldErrors) {
        customFieldErrors = new ArrayList<>();

        fieldErrors.forEach(error -> {
            customFieldErrors.add(new CustomFieldError(
                    error.getCodes()[0],
                    error.getRejectedValue(),
                    error.getDefaultMessage()
            ));
        });
    }

    public static class CustomFieldError {
        private String field;
        private Object value;
        private String reason;

        public CustomFieldError(String field, Object value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public String getField() {
            return field;
        }

        public Object getValue() {
            return value;
        }

        public String getReason() {
            return reason;
        }

    }


}