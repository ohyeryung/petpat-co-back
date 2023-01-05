package com.smile.petpat.post.rehoming.controller;

import com.smile.petpat.common.response.SuccessResponse;
import com.smile.petpat.post.rehoming.domain.RehomingCommand;
import com.smile.petpat.post.rehoming.domain.RehomingInfo;
import com.smile.petpat.post.rehoming.dto.RehomingReqDto;
import com.smile.petpat.post.rehoming.service.RehomingService;
import com.smile.petpat.user.service.UserDetailsImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Api(tags = {"post_rehoming_api"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rehoming")
public class RehomingController {

    private final RehomingService rehomingService;

    /*
    * 1. 분양 글 등록 */
    @ApiOperation(value = "분양게시글 등록", notes = "분양게시글 등록")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public SuccessResponse registerRehoming(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                  @RequestPart List<MultipartFile> rehomingImg,
                                  @RequestPart RehomingReqDto rehomingDto) {
        RehomingCommand rehomingCommand = rehomingDto.toCommand();
        rehomingService.registerRehoming(userDetails.getUser(), rehomingImg, rehomingCommand);
        return SuccessResponse.success("OK");
    }

    /*
    * 2. 분양 글 목록 조회 (페이징 처리 전) */
    @ApiOperation(value = "분양게시글 목록 조회", notes = "분양게시글 목록 조회")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public SuccessResponse listRehoming() {
        List<RehomingInfo> rehomingInfos = rehomingService.listRehoming();
        return SuccessResponse.success(rehomingInfos, "OK");
    }

    /*
    * 3. 분양 글 상세 조회 */
    @ApiOperation(value = "분양게시글 상세 조회", notes = "분양게시글 상세 조회")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public SuccessResponse detailRehoming(@RequestParam Long postId) {
        return SuccessResponse.success(rehomingService.detailRehoming(postId), "OK");
    }

    /* 4. 분양 글 수정 */
    @ApiOperation(value = "분양게시물 수정", notes = "분양 게시물 수정")
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public SuccessResponse updateRehoming(@AuthenticationPrincipal UserDetailsImpl userDetails,
                               @RequestPart List<MultipartFile> rehomingImg,
                               @RequestParam Long postId, @RequestPart RehomingReqDto rehomingDto) {
        RehomingCommand rehomingCommand = rehomingDto.toCommand();
        return SuccessResponse.success(rehomingService.updateRehoming(userDetails.getUser(), postId, rehomingCommand, rehomingImg), "OK");
    }

    /* 5. 분양 글 삭제 */
    @ApiOperation(value = "분양게시물 삭제", notes = "분양 게시물 삭제")
    @RequestMapping(value = "",method = RequestMethod.DELETE)
    public SuccessResponse deleteRehoming(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam Long postId) {
        rehomingService.deleteRehoming(userDetails.getUser(), postId);
        return SuccessResponse.success("OK");
    }
}
