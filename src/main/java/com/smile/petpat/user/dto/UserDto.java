package com.smile.petpat.user.dto;

import com.smile.petpat.user.domain.UserCommand;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class UserDto {


    @Getter
    @Setter
    public static class RegisterUserRequest{

        @NotEmpty
        private String userId;
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
        @NotEmpty
        private MultipartFile profileImg;

        public UserCommand toCommand(){
            return UserCommand.builder()
                    .userId(userId)
                    .username(username)
                    .password(password)
                    .profileImg(profileImg)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class RegisterUsernameChk{
        @NotEmpty
        private String username;


    }

    @Getter
    public static class Login{
        @NotEmpty
        private String userId;
        @NotEmpty
        private String password;
    }
}
