package com.smile.petpat.exception;

import lombok.Data;
import lombok.Getter;

@Data
public class StatusMessage {

    private String msg;

    public StatusMessage(){
        this.msg = null;
    }

    public StatusMessage(String msg) {
        this.msg = msg;
    }

    @Getter
    public enum StatusEnum{
        OK(200, "OK"),
        BAD_REQUEST(400, "BAD_REQUEST"),
        NOT_FOUND(404, "NOT_FOUND");

        int statusCode;
        String code;

        StatusEnum(int statusCode, String code) {
            this.statusCode = statusCode;
            this.code = code;
        }
    }
}