package com.smile.petpat.post.rehoming.domain;

public interface RehomingReader {
    Rehoming readRehomingById(Long rehomingId);
    void userChk(Long userId, Rehoming rehoming);
}
