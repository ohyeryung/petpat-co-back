package com.smile.petpat.user.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDto {


    @Getter
    @Setter
    public static class RegisterUserRequest{
        private String username;
        private String password;
        private String profileUrl;

    }
}
