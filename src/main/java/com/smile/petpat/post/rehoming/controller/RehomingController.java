package com.smile.petpat.post.rehoming.controller;

import com.smile.petpat.common.response.SuccessResponse;
import com.smile.petpat.post.rehoming.domain.RehomingCommand;
import com.smile.petpat.post.rehoming.dto.RehomingPagingDto;

import com.smile.petpat.post.rehoming.service.RehomingServiceImpl;
import com.smile.petpat.user.service.UserDetailsImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Api(tags = {"post_rehoming_api"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rehoming")
public class RehomingController {

    private final RehomingServiceImpl rehomingService;

    /*
    * 1. 분양 글 등록 */
    @ApiOperation(value = "분양게시글 등록", notes = "분양게시글 등록")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public SuccessResponse registerRehoming(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                  @RequestPart List<MultipartFile> rehomingImg,
                                  @RequestPart RehomingCommand rehomingDto) {
        RehomingCommand rehomingCommand = rehomingDto.toCommand();
        rehomingService.registerRehoming(userDetails.getUser(), rehomingImg, rehomingCommand);
        return SuccessResponse.success("OK");
    }

    /*
    * 2. 분양 글 목록 조회 */
    @ApiOperation(value = "분양게시글 목록 조회", notes = "분양게시글 목록 조회")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public SuccessResponse listRehoming(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @PageableDefault Pageable pageable) {
        RehomingPagingDto rehomingInfos;
        if (userDetails == null) {
            rehomingInfos = rehomingService.listRehoming(pageable);
        } else {
            rehomingInfos = rehomingService.listRehomingForMember(userDetails.getUser(), pageable);
        }
        return SuccessResponse.success(rehomingInfos, "OK");
    }

    /*
     * 3. 분양 글 상세 조회 */
    @ApiOperation(value = "분양게시글 상세 조회", notes = "분양게시글 상세 조회")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public SuccessResponse detail(@RequestParam Long postId,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return SuccessResponse.success(rehomingService.detailRehoming(postId), "OK");
        }
        return SuccessResponse.success(rehomingService.detailRehomingForMember(postId, userDetails.getUser()), "OK");
    }

    /* 4. 분양 글 수정 */
    @ApiOperation(value = "분양게시물 수정", notes = "분양 게시물 수정")
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public SuccessResponse updateRehoming(@AuthenticationPrincipal UserDetailsImpl userDetails,
                               @RequestPart List<MultipartFile> rehomingImg,
                               @RequestParam Long postId, @RequestPart RehomingCommand rehomingDto) {
        RehomingCommand rehomingCommand = rehomingDto.toCommand();
        return SuccessResponse.success(rehomingService.updateRehoming(userDetails.getUser(), postId, rehomingCommand, rehomingImg), "OK");
    }

    /* 5. 분양 글 삭제 */
    @ApiOperation(value = "분양게시물 삭제", notes = "분양 게시물 삭제")
    @RequestMapping(value = "",method = RequestMethod.DELETE)
    public SuccessResponse deleteRehoming(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                          @RequestParam Long postId) {
        rehomingService.deleteRehoming(userDetails.getUser(), postId);
        return SuccessResponse.success("OK");
    }

    @ApiOperation(value = "분양게시물 분양 중")
    @RequestMapping(value = "/statusFinding", method = RequestMethod.POST)
    public SuccessResponse updateStatusFinding(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                @RequestParam Long postId) {
        rehomingService.updateStatusFinding(userDetails.getUser(), postId);
        return SuccessResponse.success("OK");
    }

    @ApiOperation(value = "분양게시물 예약 중")
    @RequestMapping(value = "/statusReserved", method = RequestMethod.POST)
    public SuccessResponse updateStatusReserved(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                @RequestParam Long postId) {
        rehomingService.updateStatusReserved(userDetails.getUser(), postId);
        return SuccessResponse.success("OK");
    }

    @ApiOperation(value = "분양게시물 예약 완료")
    @RequestMapping(value = "/statusMatched", method = RequestMethod.POST)
    public SuccessResponse updateStatusMatched(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @RequestParam Long postId) {
        rehomingService.updateStatusMatched(userDetails.getUser(), postId);
        return SuccessResponse.success("OK");
    }
}
