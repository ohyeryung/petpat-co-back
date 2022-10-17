package com.smile.petpat.user.service;

import com.smile.petpat.user.domain.*;
import lombok.RequiredArgsConstructor;
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
        User initUser = command.toEntity();
        userReader.getUserBy(initUser.getUsername());
        return userStore.store(initUser);
    }

    @Override
    public void usernameValidChk(String username) {
        userReader.getUserBy(username);
    }
}
