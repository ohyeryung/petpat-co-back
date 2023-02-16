package com.smile.petpat.user.dto;

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
    private String nickname;
    @NotEmpty
    private String userEmail;

    public SocialUserDto(Long id, String nickname, String userEmail) {
        this.id = id;
        this.nickname = nickname;
        this.userEmail = userEmail;
    }



}
