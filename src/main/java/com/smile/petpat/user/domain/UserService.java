package com.smile.petpat.user.domain;

import org.springframework.http.ResponseEntity;

public interface UserService {
    User registerUser(UserCommand command);
    void loginUser(UserCommand command);
    void userIdValidChk(String userId);

}
