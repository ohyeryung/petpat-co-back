package com.smile.petpat.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SuccessResponse<T> {
    private Result result;
    private T data;
    private String message;

    public SuccessResponse() {
    }

    public SuccessResponse(Result result, T data, String message) {
        this.result = result;
        this.data = data;
        this.message = message;
    }

    public static <T> SuccessResponse<T> success(T data, String message) {
        return (SuccessResponse<T>) SuccessResponse.builder()
                .result(Result.SUCCESS)
                .data(data)
                .message(message)
                .build();
    }

    public static <T> SuccessResponse<T> success(T data) {
        return success(data, "");
    }

    public static <T> SuccessResponse<T> noDataSuccess(String message) {
        return success(null, message);
    }

    public enum Result {
        SUCCESS, FAIL
    }
}
