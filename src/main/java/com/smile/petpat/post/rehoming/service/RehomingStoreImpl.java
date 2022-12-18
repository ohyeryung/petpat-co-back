package com.smile.petpat.post.rehoming.service;

import com.smile.petpat.post.rehoming.domain.Rehoming;
import com.smile.petpat.post.rehoming.domain.RehomingStore;
import com.smile.petpat.post.rehoming.repository.RehomingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RehomingStoreImpl implements RehomingStore {
    private final RehomingRepository rehomingRepository;

    @Override
    public Rehoming store(Rehoming initRehoming) {
        return rehomingRepository.save(initRehoming);
    }
}
