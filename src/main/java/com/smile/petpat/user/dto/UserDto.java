package com.smile.petpat.user.dto;

import com.smile.petpat.user.domain.UserCommand;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class UserDto {


    @Getter
    @Setter
    public static class RegisterUserRequest{

        private String username;
        private String password;
        private MultipartFile profileImg;

        public UserCommand toCommand(){
            return UserCommand.builder()
                    .username(username)
                    .password(password)
                    .profileImg(profileImg)
                    .build();
        }

    }
    @Getter
    @Setter
    public static class RegisterUsernameChk{
        private String username;


    }
}
