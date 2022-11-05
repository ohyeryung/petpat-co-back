package com.smile.petpat.user.domain;

import org.springframework.http.ResponseEntity;

public interface UserService {
    User registerUser(UserCommand command);
    ResponseEntity<?> loginUser(UserCommand command);
    void userIdValidChk(String userEmail);

}
