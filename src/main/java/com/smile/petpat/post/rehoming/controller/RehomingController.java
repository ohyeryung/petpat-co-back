package com.smile.petpat.post.rehoming.controller;

import com.smile.petpat.post.rehoming.dto.RehomingPagingDto;
import com.smile.petpat.post.rehoming.dto.RehomingReqDto;
import com.smile.petpat.post.rehoming.dto.RehomingResDto;
import com.smile.petpat.post.rehoming.service.RehomingService;
import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rehoming")
public class RehomingController {

    private final RehomingService rehomingService;

     //분양 글 등록
    @PostMapping("")
    public ResponseEntity<RehomingResDto> create(@AuthenticationPrincipal UserDetailsImpl userImpl, @RequestPart List<MultipartFile> rehomingImg,
                                                 @RequestPart(value = "RehomingRequestBody") RehomingReqDto requestDto) {
        return ResponseEntity.status(200).body(rehomingService.createRehoming(userImpl.getUser(), rehomingImg, requestDto));
    }

    // 분양 글 목록 조회
    @GetMapping("")
    public ResponseEntity<RehomingPagingDto> read(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(200).body(rehomingService.readRehoming(pageable));
    }

    // 분양 글 상세 조회

    // 분양 글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<Long> put(@PathVariable Long postId, @RequestBody RehomingReqDto rehomingDto) {
        return ResponseEntity.status(200).body(rehomingService.putRehoming(postId, rehomingDto));
    }
}
