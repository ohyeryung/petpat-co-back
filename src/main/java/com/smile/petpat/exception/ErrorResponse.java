package com.smile.petpat.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private String msg;

    public ErrorResponse(){
        this.msg = null;
    }

    public ErrorResponse(String msg) {
        this.msg = msg;
    }

}