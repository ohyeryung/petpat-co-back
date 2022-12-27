package com.smile.petpat.post.qna.controller;

import com.smile.petpat.post.qna.service.QnaService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"post_qna_api"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qna")
public class QnaController {


    /**
     * Qna 게시물 등록
     * @return 성공 시 200 Success 반환
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public void qnaAdd(){

    }

    /**
     * Qna 게시물 목록 조회
     * @return 성공 시 200 Success 반환
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public void qnaList(){

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
