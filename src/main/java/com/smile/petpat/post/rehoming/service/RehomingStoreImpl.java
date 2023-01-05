package com.smile.petpat.post.rehoming.service;

import com.smile.petpat.post.rehoming.domain.Rehoming;
import com.smile.petpat.post.rehoming.domain.RehomingStore;
import com.smile.petpat.post.rehoming.repository.RehomingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RehomingStoreImpl implements RehomingStore {
    private final RehomingRepository rehomingRepository;
    private final RehomingReaderImpl rehomingReader;

    /* 1. 분양 게시글 등록*/
    @Override
    public Rehoming store(Rehoming initRehoming) {
        return rehomingRepository.save(initRehoming);
    }

    /* 2. 분양 게시글 수정 */
    @Override
    public Rehoming update(Rehoming rehoming, Long userId, Long postId) {
        rehomingReader.userChk(userId, postId);
        return rehomingRepository.save(rehoming);
    }

    /* 3. 분양 게시글 삭제 */
    @Override
    public void delete(Long userId, Long postId) {
        rehomingReader.userChk(userId, postId);
        rehomingRepository.deleteById(postId);
    }
}
