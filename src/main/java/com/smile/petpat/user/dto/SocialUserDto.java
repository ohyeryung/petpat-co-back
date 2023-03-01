package com.smile.petpat.user.dto;

import com.smile.petpat.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

@Getter
@NoArgsConstructor
public class SocialUserDto {
    private Long id;
    @NotEmpty
    private String userEmail;
    private String nickname;
    private User.oauthEnum oauthType;


    public SocialUserDto(Long id, String userEmail, String nickname, User.oauthEnum oauthType) {
        this.id = id;
        this.userEmail = userEmail;
        this.nickname = nickname;
        this.oauthType = oauthType;
    }



}
