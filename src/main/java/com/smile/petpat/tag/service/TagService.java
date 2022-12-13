package com.smile.petpat.tag.service;

import com.smile.petpat.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterator;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    /* 태그 저장 (max 10개) */
    public void saveTag(List<String> tagList) {

        StringBuilder sb = new StringBuilder();
        for (String tag : tagList) {
            sb.append(tag);
            sb.append(",");
        }
        log.info("sb = " + sb);
    }
}
