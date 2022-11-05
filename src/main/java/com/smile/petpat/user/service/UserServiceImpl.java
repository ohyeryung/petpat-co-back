package com.smile.petpat.user.service;

import com.smile.petpat.user.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserReader userReader;
    private final UserStore userStore;
    private final UserAuth userAuth;

    @Override
    @Transactional
    public User registerUser(UserCommand command) {

        userReader.getUserByUserEmail(command.getUserEmail());
        return userStore.store(command.toEntity());
    }

    @Override
    @Transactional
    public ResponseEntity<?> loginUser(UserCommand command) {

        User initUser = command.toLogin();
        userReader.getUser(initUser);
        return userAuth.getToken(initUser);
    }

    @Override
    public void userIdValidChk(String userId) {
        userReader.getUserByUserEmail(userId);
    }

}
