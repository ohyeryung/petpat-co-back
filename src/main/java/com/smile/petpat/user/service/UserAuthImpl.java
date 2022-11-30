package com.smile.petpat.user.service;

import com.smile.petpat.jwt.JwtTokenUtils;
import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.domain.UserAuth;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserAuthImpl implements UserAuth {

    private final JwtTokenUtils jwtTokenUtils;

    public UserAuthImpl(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    public ResponseEntity<String> getToken(User user) {

        System.out.println("UserAuthImpl Class getToken method 실행");

        HttpHeaders headers = jwtTokenUtils.headerToken(user);

        System.out.println("headers.getOrigin() = " + headers.getOrigin());

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(null);
    }

}
