package com.smile.petpat.user.domain;

import org.springframework.http.ResponseEntity;

public interface UserAuth {
    ResponseEntity<String> getToken(User user);
}
