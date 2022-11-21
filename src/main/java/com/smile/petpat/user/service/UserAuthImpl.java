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

        HttpHeaders headers = jwtTokenUtils.headerToken(user);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(null);
    }

}
