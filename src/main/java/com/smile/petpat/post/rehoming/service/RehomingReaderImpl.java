package com.smile.petpat.post.rehoming.service;

import com.smile.petpat.post.rehoming.domain.Rehoming;
import com.smile.petpat.post.rehoming.domain.RehomingReader;
import com.smile.petpat.post.rehoming.repository.RehomingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RehomingReaderImpl implements RehomingReader {
    private final RehomingRepository rehomingRepository;

    public List<Rehoming> readRehomingList() {
        return rehomingRepository.findAll();
    }
}
