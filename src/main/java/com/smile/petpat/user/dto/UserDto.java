package com.smile.petpat.user.dto;

import com.smile.petpat.user.domain.UserCommand;
import com.smile.petpat.user.domain.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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

        private UserRole userRole;

        public UserCommand toCommand(){
            return UserCommand.builder()
                    .userEmail(userEmail)
                    .nickname(nickname)
                    .password(password)
                    .profileImgPath(profileImgPath)
                    .userRole(userRole)
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

    @Getter
    @Setter
    public static class ModifyUserRequest{
        private String username;
        private String email;
        private String profileImgUrl;
        private MultipartFile profileImgFile;
    }

    @Getter
    @Setter
    public static class ModifyPasswordRequest{
        private String newPassword;
        private String newPasswordChk;
    }

    @Getter
    @Setter
    public static class CheckPasswordRequest{
        private String password;
    }
}
