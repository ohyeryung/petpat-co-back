package com.smile.petpat.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    // User
    ILLEGAL_USERNAME_DUPLICATION(HttpStatus.SC_BAD_REQUEST, "80400", "중복된 아이디입니다."),
    ILLEGAL_USER_NOT_EXIST(HttpStatus.SC_BAD_REQUEST, "80400","가입되지 않은 아이디입니다."),
    ILLEGAL_PASSWORD_NOT_VALID (HttpStatus.SC_BAD_REQUEST, "80400","비밀번호가 일치하지 않습니다."),
    ILLEGAL_INVALID_TOKEN(HttpStatus.SC_BAD_REQUEST, "80400","유효한 토큰이 아닙니다."),

    // Image
    FAILIED_UPLOAD_IMAGE(HttpStatus.SC_BAD_REQUEST, "80400", "이미지 업로드에 실패했습니다."),
    WRONG_TYPE_IMAGE(HttpStatus.SC_BAD_REQUEST, "80400", "잘못된 형식의 파일입니다");

    private final int httpStatus;
    private final String code;
    private final String message;
}
