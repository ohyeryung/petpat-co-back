package com.smile.petpat.post.rehoming.repository.querydsl;

import com.smile.petpat.post.rehoming.domain.RehomingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RehomingRepositoryQuerydsl {
    Page<RehomingInfo> rehomingListForMember(Long userId, Pageable pageable);
    Page<RehomingInfo> rehomingList(Pageable pageable);
}
