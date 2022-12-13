package com.smile.petpat.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {

    // User
    ILLEGAL_USERNAME_DUPLICATION(HttpStatus.BAD_REQUEST,  "중복된 아이디입니다."),
    ILLEGAL_USER_NOT_EXIST(HttpStatus.BAD_REQUEST, "가입되지 않은 아이디입니다."),
    ILLEGAL_PASSWORD_NOT_VALID (HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    ILLEGAL_INVALID_TOKEN(HttpStatus.BAD_REQUEST, "유효한 토큰이 아닙니다."),
    INVALID_PASSWORD_PATTERN(HttpStatus.BAD_REQUEST,  "비밀번호는 8자 이상 12자 이하의 길이의 영문자, 숫자, 특수문자(!@#$%^&*)만 사용 가능합니다."),

    // Image
    FAILED_UPLOAD_IMAGE(HttpStatus.BAD_REQUEST,  "이미지 업로드에 실패했습니다."),
    WRONG_TYPE_IMAGE(HttpStatus.BAD_REQUEST,  "잘못된 형식의 파일입니다");

    // post

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus,  String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
