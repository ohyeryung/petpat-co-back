package com.smile.petpat.user.domain;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    User registerUser(UserCommand command);
    String loginUser(UserCommand command);

    String kakaoUserLogin(String code) throws JsonProcessingException;

    void userIdValidChk(String userEmail);

}
