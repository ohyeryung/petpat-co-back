package com.smile.petpat.post.rehoming.controller;

import com.smile.petpat.common.response.SuccessResponse;
import com.smile.petpat.post.rehoming.domain.Rehoming;
import com.smile.petpat.post.rehoming.domain.RehomingCommand;
import com.smile.petpat.post.rehoming.domain.RehomingInfo;
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

    /*
    * 1. 분양 글 등록 */
    @PostMapping("")
    public SuccessResponse create(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                  @RequestPart List<MultipartFile> rehomingImg,
                                  @RequestPart RehomingReqDto rehomingDto) {
        RehomingCommand rehomingCommand = rehomingDto.toCommand();
        rehomingService.createRehoming(userDetails.getUser(), rehomingImg, rehomingCommand);
        return SuccessResponse.success("OK");
    }

    /*
    * 2.  분양 글 목록 조회 */
    @GetMapping("")
    public SuccessResponse read(@RequestParam Long pageno, @PageableDefault Pageable pageable) {
        return SuccessResponse.success(rehomingService.readRehoming(pageable),"OK");
    }

    /*
    * 2-1. 분양 글 목록 조회 (페이징 처리 전) */
    @GetMapping("/test")
    public SuccessResponse listRehoming() {
        List<RehomingInfo> rehomingInfos = rehomingService.listRehoming();
        return SuccessResponse.success(rehomingInfos, "OK");
    }

    /*
    * 3. 분양 글 상세 조회 */
    @GetMapping("/detail")
    public SuccessResponse detail(@RequestParam Long postId) {
        return SuccessResponse.success(rehomingService.detailRehoming(postId), "OK");
    }

    /* 4. 분양 글 수정 */
    @PutMapping("")
    public SuccessResponse put(@AuthenticationPrincipal UserDetailsImpl userDetails,
                               @RequestPart List<MultipartFile> rehomingImg,
                               @RequestParam Long postId, @RequestPart RehomingReqDto rehomingDto) {
        RehomingCommand rehomingCommand = rehomingDto.toCommand();
        return SuccessResponse.success(rehomingService.putRehoming(userDetails.getUser(), postId, rehomingCommand, rehomingImg), "OK");
    }

    /* 5. 분양 글 삭제 */
    @DeleteMapping("")
    public SuccessResponse delete(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam Long postId) {
        rehomingService.deleteRehoming(userDetails.getUser(), postId);
        return SuccessResponse.success("OK");
    }
}
