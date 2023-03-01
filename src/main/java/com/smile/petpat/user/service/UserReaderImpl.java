package com.smile.petpat.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile.petpat.common.exception.CustomException;

import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.domain.UserReader;
import com.smile.petpat.user.dto.SocialUserDto;
import com.smile.petpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.smile.petpat.common.response.ErrorCode.*;


@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void getUserByUserEmail(String userEmail) {
        userRepository.findByUserEmail(userEmail).ifPresent(
                user -> {
                    throw new CustomException(ILLEGAL_USERNAME_DUPLICATION);
                }
        );
    }

    @Override
    public void getUser(User initUser) {
        isPwValid(initUser);
    }

    @Override
    public SocialUserDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        Long id = jsonNode.get("id").asLong();
        String email = jsonNode.get("kakao_account").get("email").asText();
        String nickname = jsonNode.get("properties").get("nickname").asText();

        return new SocialUserDto(id, email, nickname);
    }

    public void isPwValid(User initUser) {
        User foundUser = userRepository.findByUserEmail(initUser.getUserEmail())
                .orElseThrow(() -> new CustomException(ILLEGAL_USER_NOT_EXIST));
        if (!passwordEncoder.matches(initUser.getPassword(), foundUser.getPassword())) {
            throw new CustomException(ILLEGAL_PASSWORD_NOT_VALID);
        }
    }

}
