package com.smile.petpat.user.domain;

import com.smile.petpat.user.dto.UserDto;

public interface UserModify {
    User modifyProfile(UserDto.ModifyUserRequest request, User user);
    Boolean passwordCheck(String password, User user);
    User modifyPassword(UserDto.ModifyPasswordRequest request,User user);
}
