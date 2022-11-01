package com.smile.petpat.user.service;

import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.domain.UserCommand;
import com.smile.petpat.user.domain.UserReader;
import com.smile.petpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.smile.petpat.exception.ExceptionMessage.*;

@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void getUserByUserId(String userId) {
        userRepository.findByUserId(userId).ifPresent(
                user -> {
                    throw new IllegalArgumentException(ILLEGAL_USERNAME_DUPLICATION);
                }
        );
    }

    @Override
    public void getUser(User initUser) {
        pwCheck(initUser);
    }

    public void pwCheck(User initUser) {
        User foundUser = userRepository.findByUserId(initUser.getUserId()).orElseThrow(IllegalArgumentException::new);
        if (!passwordEncoder.matches(initUser.getPassword(), foundUser.getPassword())) {
            throw new IllegalArgumentException(ILLEGAL_PASSWORD_NOT_VALID);
        }
    }

}
