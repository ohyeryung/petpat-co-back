package com.smile.petpat.post.qna.controller;

import com.smile.petpat.post.qna.domain.QnaCommand;
import com.smile.petpat.post.qna.domain.QnaDto;
import com.smile.petpat.post.qna.service.QnaService;
import com.smile.petpat.user.service.UserDetailsImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public void qnaAdd(@Valid QnaDto.CommonQna qnaDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        QnaCommand command = qnaDto.toCommand(userDetails.getUser());
        qnaService.addQna(command);
    }

    /**
     * Qna 게시물 목록 조회
     * @return 성공 시 200 Success 반환
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public void qnaList(){
        qnaService.listQna();

    }

    /**
     * Qna 게시물 수정
     * @return 성공 시 200 Success 반환
     */
    @RequestMapping(value = "/{postId}",method = RequestMethod.PUT)
    public void qnaModify(@PathVariable String postId){

    }

    /**
     * Qna 게시물 삭제
     * @return 성공 시 200 Success 반환
     */
    @RequestMapping(value = "/{postId}",method = RequestMethod.DELETE)
    public void qnaRemove(@PathVariable String postId){

    }
}
