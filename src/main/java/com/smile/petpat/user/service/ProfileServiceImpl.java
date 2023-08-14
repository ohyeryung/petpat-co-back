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
    public ProfileDto.ProfileResponse getProfile(User user) {
        return new ProfileDto.ProfileResponse(user);
    }

    @Override
    public Page<ProfileDto.RehomingResponse> getMyRehoming(User user, Pageable pageable) {
        return profileRepository.getMyRehoming(user.getId(),pageable);
    }

    @Override
    public Page<ProfileDto.TradeResponse> getMyTrade(User user, Pageable pageable) {
        return profileRepository.getMyTrade(user.getId(),pageable);
    }

    @Override
    public Page<ProfileDto.QnaResponse> getMyQna(User user, Pageable pageable) {

        return profileRepository.getMyQna(user.getId(),pageable);
    }

    @Override
    public Page<ProfileDto.CommentResponse> getMyComment(User user, Pageable pageable) {

        return profileRepository.getMyComment(user.getId(),pageable);
    }
}
