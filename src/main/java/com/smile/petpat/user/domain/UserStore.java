package com.smile.petpat.user.domain;

import com.smile.petpat.user.dto.SocialUserDto;

public interface UserStore {
    User store(User intiUser);
    User socialStore(SocialUserDto socialUser);
}
