package com.smile.petpat.user.service;

import com.smile.petpat.common.exception.CustomException;
import com.smile.petpat.common.response.ErrorCode;
import com.smile.petpat.image.util.ImageUtils;
import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.domain.UserCommand;
import com.smile.petpat.user.domain.UserModify;
import com.smile.petpat.user.dto.UserDto;
import com.smile.petpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserModifyImpl implements UserModify {

    private final UserRepository userRepository;
    private final ImageUtils imageUtils;
    private final PasswordEncoder passwordEncoder;

    /**프로필 수정
     * userDetails 확인 필요
     */
    @Override
    @Transactional
    public User modifyProfile(UserDto.ModifyUserRequest request, User loginUser) {
        User user = userRepository.findById(loginUser.getId()).orElseThrow(
                ()->new CustomException(ErrorCode.ILLEGAL_USER_NOT_EXIST)
        );

        String filepath = imageUtils.saveProfileImage(request.getProfileImgFile(),
                request.getProfileImgUrl());

        UserCommand userCommand = new UserCommand(request,filepath);
        user.modifyProfile(userCommand);
        return userRepository.save(user);
    }

    /**비밀번호 확인
     *
     * @param password
     * @param user
     * @return
     */
    @Override
    public Boolean passwordCheck(String password, User user) {
        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new CustomException(ErrorCode.ILLEGAL_PASSWORD_NOT_VALID);
        return true;
    }

    /**
     * 비밀번호 수정
     * userDetails 확인 필요
     */
    @Override
    @Transactional
    public User modifyPassword(UserDto.ModifyPasswordRequest request,User loginUser) {
        if (!request.getNewPassword().equals(request.getNewPasswordChk()))
            throw new CustomException(ErrorCode.ILLEGAL_PASSWORD_NOT_CORRECT);

        User user = userRepository.findById(loginUser.getId()).orElseThrow(
                ()->new CustomException(ErrorCode.ILLEGAL_USER_NOT_EXIST)
        );

        user.modifyPassword(passwordEncoder.encode(request.getNewPassword()));
        return userRepository.save(user);
    }
}
