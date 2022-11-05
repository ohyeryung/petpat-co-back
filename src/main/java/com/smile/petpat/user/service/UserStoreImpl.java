package com.smile.petpat.user.service;

import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.domain.UserStore;
import com.smile.petpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
        User user = User.builder()
                .userEmail(initUser.getUserEmail())
                .nickname(initUser.getNickname())
                .password(passwordEncoder.encode(initUser.getPassword()))
                .profileImgPath(initUser.getProfileImgPath())
                .build();
        return userRepository.save(user);
    }

    // 유저 객체 검증
    public void validChk(User initUser){
        if(StringUtils.isEmpty(initUser.getUserEmail())) throw new IllegalArgumentException();
        if(StringUtils.isEmpty(initUser.getPassword())) throw new IllegalArgumentException();
    }

}
