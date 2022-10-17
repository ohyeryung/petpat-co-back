package com.smile.petpat.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@ToString
public class UserCommand {

    private String username;
    private String password;
    private MultipartFile profileImg;


    public UserCommand(String username, String password, MultipartFile profileImg) {
        this.username = username;
        this.password = password;
        this.profileImg = profileImg;
    }

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
