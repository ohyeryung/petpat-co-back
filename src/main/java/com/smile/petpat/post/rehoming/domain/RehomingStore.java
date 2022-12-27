package com.smile.petpat.post.rehoming.domain;

public interface RehomingStore {
    Rehoming store(Rehoming rehoming);
    Rehoming update(Rehoming rehoming, Long userId, Long postId);
    void delete(Long userId, Long postId);
}
