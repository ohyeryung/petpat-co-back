package com.smile.petpat.user.domain;

public interface UserReader {
    void getUserByUserId(String userId);
    void getUser(User user);
}
