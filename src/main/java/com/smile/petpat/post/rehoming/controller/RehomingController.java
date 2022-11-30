package com.smile.petpat.post.rehoming.controller;

import com.smile.petpat.post.rehoming.domain.Rehoming;
import com.smile.petpat.post.rehoming.dto.RehomingDto;
import com.smile.petpat.post.rehoming.dto.RehomingPagingDto;
import com.smile.petpat.post.rehoming.dto.RehomingResponseDto;
import com.smile.petpat.post.rehoming.service.RehomingService;
import com.smile.petpat.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<RehomingResponseDto> create(@AuthenticationPrincipal User user, @RequestPart List<MultipartFile> rehomingImg,
                                                      @RequestPart(value = "RehomingRequestBody") RehomingDto requestDto) {
        return ResponseEntity.status(200).body(rehomingService.createRehoming(user, rehomingImg, requestDto));
    }

    // 분양 글 조회
    @GetMapping("")
    public ResponseEntity<RehomingPagingDto> read(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(200).body(rehomingService.readRehoming(pageable));
    }

    // 분양 글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<Long> put(@PathVariable Long postId, @RequestBody RehomingDto rehomingDto) {
        return ResponseEntity.status(200).body(rehomingService.putRehoming(postId, rehomingDto));
    }
}
