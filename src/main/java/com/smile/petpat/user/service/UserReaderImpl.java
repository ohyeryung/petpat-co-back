package com.smile.petpat.user.service;

import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.domain.UserReader;
import com.smile.petpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.smile.petpat.exception.ExceptionMessage.*;

@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void getUserByUserEmail(String userEmail) {
        userRepository.findByUserEmail(userEmail).ifPresent(
                user -> {
                    throw new IllegalArgumentException(ILLEGAL_USERNAME_DUPLICATION);
                }
        );
    }

    @Override
    public void getUser(User initUser) {
        isPwValid(initUser);
    }

    public void isPwValid(User initUser) {
        User foundUser = userRepository.findByUserEmail(initUser.getUserEmail())
                .orElseThrow(() -> new IllegalArgumentException(ILLEGAL_USER_NOT_EXIST));
        if (!passwordEncoder.matches(initUser.getPassword(), foundUser.getPassword())) {
            throw new IllegalArgumentException(ILLEGAL_PASSWORD_NOT_VALID);
        }
    }

}
