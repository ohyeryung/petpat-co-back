package com.smile.petpat.user.service;

import com.smile.petpat.user.domain.ProfileService;
import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.dto.ProfileDto;
import com.smile.petpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository profileRepository;
    @Override
    public Page<ProfileDto.RehomingResponse> getMyRehoming(User user, Pageable pageable) {
        return profileRepository.getMyRehoming(user.getId(),pageable);
    }

    @Override
    public ProfileDto.TradeResponse getMyTrade() {
        return null;
    }

    @Override
    public ProfileDto.QnaResponse getMyQna() {
        return null;
    }

    @Override
    public ProfileDto.CommentResponse getMyComment() {
        return null;
    }
}
