package com.smile.petpat.post.rehoming.service;

import com.smile.petpat.post.rehoming.domain.Rehoming;
import com.smile.petpat.post.rehoming.domain.RehomingReader;
import com.smile.petpat.post.rehoming.repository.RehomingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RehomingReaderImpl implements RehomingReader {
    private final RehomingRepository rehomingRepository;

    public Rehoming readRehomingById(Long rehomingId) {
        return rehomingRepository.findById(rehomingId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물입니다.")
        );
    }
    public void userChk(Long userId, Rehoming rehoming) {
        if(!rehoming.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("본인 글만 수정/삭제가 가능합니다.");
        }
    }
}
