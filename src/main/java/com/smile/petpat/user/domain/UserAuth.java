package com.smile.petpat.user.domain;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserAuth {
    String getToken(User user);
    String getKakaoAccessToken(String code) throws JsonProcessingException;
    String getGoogleAccessToken(String code) throws JsonProcessingException;
    String getGithubAccessToken(String code) throws JsonProcessingException;

    String forceLogin(User socialUser);
}
