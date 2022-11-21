package com.smile.petpat.rehoming.controller;

import com.smile.petpat.rehoming.domain.Rehoming;
import com.smile.petpat.rehoming.dto.RehomingDto;
import com.smile.petpat.rehoming.dto.RehomingPagingDto;
import com.smile.petpat.rehoming.service.RehomingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rehoming")
public class RehomingController {

    private final RehomingService rehomingService;
    // 분양 글 등록
    @PostMapping("")
    public ResponseEntity<Rehoming> create( @RequestPart List<MultipartFile> rehomingImg,
                          @RequestPart(value = "requestDto") RehomingDto requestDto) {
        return ResponseEntity.status(200).body(rehomingService.createRehoming(rehomingImg, requestDto));
    }

    // 분양 글 조회
    @GetMapping("")
    public ResponseEntity<RehomingPagingDto> read(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(200).body(rehomingService.readRehoming(pageable));
    }

    // 분양 글 수정
    @PutMapping("")
    public ResponseEntity put(@RequestParam int postId) {
        return ResponseEntity.status(200).body(rehomingService.putRehoming(postId));
    }
}
