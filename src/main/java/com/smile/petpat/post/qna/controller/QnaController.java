package com.smile.petpat.post.qna.controller;

import com.smile.petpat.common.response.SuccessResponse;
import com.smile.petpat.post.qna.domain.QnaCommand;
import com.smile.petpat.post.qna.domain.QnaDto;
import com.smile.petpat.post.qna.service.QnaService;
import com.smile.petpat.post.rehoming.dto.RehomingPagingDto;
import com.smile.petpat.user.service.UserDetailsImpl;
import com.sun.net.httpserver.Authenticator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"post_qna_api"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qna")
public class QnaController {

    private final QnaService qnaService;

    /**
     * Qna 게시물 등록
     * @return 성공 시 200 Success 반환
     */
    @ApiOperation(value = "Qna 게시물 등록", notes = "Qna 게시물 등록")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public SuccessResponse registerQna(@ModelAttribute @Valid QnaDto.CommonQna qnaDto,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails){
        QnaCommand qnaCommand = qnaDto.toCommand();
        qnaService.registerQna(qnaCommand, userDetails.getUser());
        return SuccessResponse.success("ok");
    }

    /**
     * Qna 게시물 목록 조회
     * @return 성공 시 200 Success 반환
     */
    @ApiOperation(value = "QNA 목록 조회", notes = "QNA 목록 조회")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public SuccessResponse listQna(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                   @PageableDefault() Pageable pageable){
        return SuccessResponse.success(qnaService.listQna(userDetails.getUser(), pageable), "ok");

    }

}
