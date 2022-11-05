package com.smile.petpat.user.domain;

import org.springframework.http.ResponseEntity;

public interface UserAuth {
    ResponseEntity<?> getToken(User user);
}
