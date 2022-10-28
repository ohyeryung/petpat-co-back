package com.smile.petpat.user.service;

import com.smile.petpat.user.domain.UserReader;
import com.smile.petpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.smile.petpat.exception.ExceptionMessage.ILLEGAL_USERNAME_DUPLICATION;

@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {
    private final UserRepository userRepository;

    @Override
    public void getUserByUserId(String username) {
        userRepository.findByUsername(username).ifPresent(
                user -> {
                    throw new IllegalArgumentException(ILLEGAL_USERNAME_DUPLICATION);
                }
        );
    }
}
