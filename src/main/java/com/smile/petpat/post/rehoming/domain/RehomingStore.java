package com.smile.petpat.post.rehoming.domain;

public interface RehomingStore {
    Rehoming store(Rehoming rehoming);
    void delete(Long userId, Long postId);
}
