package com.smile.petpat.user.dto;

import com.smile.petpat.user.domain.UserCommand;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserDto {

    @Getter
    @Setter
    public static class RegisterUserRequest{

        @NotEmpty
        private String userEmail;
        @NotEmpty
//        @Pattern(regexp = )
        private String nickname;
        @NotEmpty
        private String password;
        @NotEmpty
        private String profileImgPath;

        public UserCommand toCommand(){
            return UserCommand.builder()
                    .userEmail(userEmail)
                    .nickname(nickname)
                    .password(password)
                    .profileImgPath(profileImgPath)
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
    public static class LoginUserRequest{
        @NotEmpty
        private String userEmail;
        @NotEmpty
        private String password;

        public UserCommand toCommand(){
            return UserCommand.builder()
                    .userEmail(userEmail)
                    .password(password)
                    .build();
        }
    }
}
