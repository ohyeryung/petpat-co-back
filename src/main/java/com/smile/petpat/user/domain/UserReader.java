package com.smile.petpat.user.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smile.petpat.user.dto.SocialUserDto;

public interface UserReader {
    void getUserByUserEmail(String userEmail);
    void getUser(User user);

    SocialUserDto getKakaoUserInfo(String accessToken) throws JsonProcessingException;
}
