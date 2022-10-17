package com.smile.petpat.user.service;

import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.domain.UserStore;
import com.smile.petpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserStoreImpl implements UserStore {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User store(User initUser) {
        validChk(initUser);
        return userRepository.save(initUser);
    }

    // 유저 객체 검증
    public void validChk(User initUser){

    }

}
