package com.smile.petpat.user.domain;

public interface UserReader {
    void getUserByUserEmail(String userEmail);
    void getUser(User user);
}
