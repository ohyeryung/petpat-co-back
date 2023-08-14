package com.smile.petpat.user.repository.querydsl;

import com.smile.petpat.user.dto.ProfileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfileRepositoryQuerydsl {
    Page<ProfileDto.RehomingResponse> getMyRehoming(Long userId, Pageable pageable);
    Page<ProfileDto.CommentResponse> getMyComment(Long userId, Pageable pageable);

    Page<ProfileDto.TradeResponse> getMyTrade(Long userId, Pageable pageable);
}
