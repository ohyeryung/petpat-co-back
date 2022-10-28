package com.smile.petpat.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@ToString
public class UserCommand {

    private final PasswordEncoder passwordEncoder;

    private String userId;
    private String username;
    private String password;
    private MultipartFile profileImg;


    public UserCommand(PasswordEncoder passwordEncoder, String userId, String username, String password, MultipartFile profileImg) {
        this.passwordEncoder = passwordEncoder;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.profileImg = profileImg;
    }

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .username(username)
                .password(password)
                .build();
    }

    public User toRegisterEntity(){
        return User.builder()
                .userId(userId)
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
    }

    // 로그인
    public User toLogin(){
        return User.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
