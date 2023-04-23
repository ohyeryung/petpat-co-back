package com.smile.petpat.user.domain;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserService {
    User registerUser(UserCommand command);
    String loginUser(UserCommand command);

    String kakaoUserLogin(String code) throws JsonProcessingException;
    String googleUserLogin(String code) throws JsonProcessingException;

    String githubUserLogin(String code) throws JsonProcessingException;
    void userIdValidChk(String userEmail);

}
