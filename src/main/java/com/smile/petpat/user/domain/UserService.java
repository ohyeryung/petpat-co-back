package com.smile.petpat.user.domain;

public interface UserService {
    User registerUser(UserCommand command);
//    User loginUser(UserCommand command);
    void userIdValidChk(String userId);

}
