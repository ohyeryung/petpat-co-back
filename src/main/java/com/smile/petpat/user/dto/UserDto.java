package com.smile.petpat.user.dto;

import com.smile.petpat.user.domain.UserCommand;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserDto {

    @Getter
    @Setter
    public static class RegisterUserRequest{

        @NotEmpty
        private String userEmail;
        @NotEmpty
        private String nickname;
        @NotEmpty
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,12}$",
                message = "비밀번호는 8자 이상 12자 이하의 길이의 영문자, 숫자, 특수문자(!@#$%^&*)만 사용 가능합니다.")
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
