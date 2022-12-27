package com.smile.petpat.post.rehoming.domain;

import java.util.List;

public interface RehomingReader {
    List<Rehoming> readRehomingList();
    Rehoming readRehomingById(Long rehomingId);
}
