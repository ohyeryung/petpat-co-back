package com.smile.petpat.user.service;

import com.smile.petpat.user.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserReader userReader;
    private final UserStore userStore;

    @Override
    @Transactional
    public User registerUser(UserCommand command) {

        User initUser = command.toRegisterEntity();
        userReader.getUserByUserId(initUser.getUserId());
        return userStore.store(initUser);
    }

    @Override
    public void userIdValidChk(String userId) {
        userReader.getUserByUserId(userId);
    }

}
