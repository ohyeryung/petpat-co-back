package com.smile.petpat.user.service;

import com.smile.petpat.user.domain.UserReader;
import com.smile.petpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {
    private final UserRepository userRepository;

    @Override
    public void getUserBy(String username) {
        userRepository.findByUsername(username).ifPresent(
                user -> {
                    throw new IllegalArgumentException("이미 존재하는 username 입니다.");
                }
        );
    }
}
