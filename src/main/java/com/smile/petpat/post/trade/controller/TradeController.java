package com.smile.petpat.post.trade.controller;

import com.smile.petpat.common.response.SuccessResponse;
import com.smile.petpat.post.trade.domain.TradeCommand;
import com.smile.petpat.post.trade.domain.TradeDto;
import com.smile.petpat.post.trade.service.TradeService;
import com.smile.petpat.user.service.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "TradeController", description = "중고거래 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trade")
public class  TradeController {

    private final TradeService tradeService;

    /**
     * 중고거래 게시물 등록
     * @return 성공 시 200 Success 반환
     */
    @Operation(summary = "중고거래 게시물 등록", description = "중고거래 게시물 등록")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public SuccessResponse registerTrade(@ModelAttribute @Valid TradeDto.CommonTrade tradeDto,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
        TradeCommand tradeCommand = tradeDto.toCommand();
        return SuccessResponse.success(tradeService.registerTrade(tradeCommand,userDetails.getUser()));
    }

    /**
     * 중고거래 게시물 목록 조회
     * @return 성공 시 200 Success 와 함께 분양게시물 목록 반환
     */
    @Operation(summary = "중고거래 게시물 목록 조회", description = "중고거래 게시물 목록 조회")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public SuccessResponse listTrade(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                     @PageableDefault() Pageable pageable){
        //TODO: 비로그인인 유저를 다르게 처리해야함, 미완료 상태
        if (userDetails.getUser().getUserEmail().split("@")[1].contains("guest")) { //비회원 유저가 조회
            return SuccessResponse.success(tradeService.listTrade(userDetails.getUser(),pageable),"ok");
        }
        // 2. 로그인 한 유저
        return SuccessResponse.success(tradeService.listTrade(userDetails.getUser(),pageable),"ok");
    }

    /**
     * 중고거래 게시물 상세 조회
     * @return 성공 시 200 Success 와 함께 분양게시물 상세 반환
     */
    @Operation(summary = "중고거래 게시물 상세조회", description = "중고거래 게시물 상세조회")
    @RequestMapping(value = "/{postId}",method = RequestMethod.GET)
    public SuccessResponse detailTrade(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails.getUser().getUserEmail().split("@")[1].contains("guest")) { //비회원 유저가 조회
            return SuccessResponse.success(tradeService.tradeDetail(postId),"ok");
        } else { //회원인 유저가 조회
            return SuccessResponse.success(tradeService.tradeDetailforUser(postId, userDetails.getUser()),"ok");
        }
    }

    /**
     * 중고거래 게시물 수정
     * @return 성공 시 200 Success 와 함께 수정한 분양게시물 반환
     */
    @Operation(summary = "중고거래 게시물 수정", description = "중고거래 게시물 수정")
    @RequestMapping(value = "/{postId}",method = RequestMethod.PUT)
    public SuccessResponse updateTrade(@ModelAttribute TradeDto.TradeUpdateDto tradeDto,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @PathVariable Long postId){
        TradeCommand tradeCommand = tradeDto.toCommand();
        return SuccessResponse.success(tradeService.updateTrade(userDetails.getUser(),postId,tradeCommand));
    }

    /**
     * 중고거래 게시물  삭제
     * @return 성공 시 200 Success 반환
     */
    @Operation(summary = "중고거래 게시물 삭제", description = "중고거래 게시물 삭제")
    @RequestMapping(value = "/{postId}",method = RequestMethod.DELETE)
    public SuccessResponse deleteTrade(@PathVariable Long postId,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        tradeService.deleteTrade(postId,userDetails.getUser());
        return SuccessResponse.success("OK");
    }
    /**
     * 인기있는 중고거래 게시물 3개 추출
     * @return 성공 시 200 Success 반환
     */
    @Operation(summary = "인기있는 중고거래 게시물", description = "인기있는 중고거래 게시물")
    @RequestMapping(value = "/trending",method = RequestMethod.GET)
    public SuccessResponse fetchTrendingTrade(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return SuccessResponse.success( tradeService.fetchTrendingTrade(userDetails.getUser()));
    }

    @Operation(summary = "중고거래 게시물 판매 중")
    @RequestMapping(value = "/statusFinding", method = RequestMethod.POST)
    public SuccessResponse updateStatusFinding(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @RequestParam Long postId) {
        return SuccessResponse.success(tradeService.updateStatusFinding(userDetails.getUser(), postId));
    }

    @Operation(summary = "중고거래 게시물 예약 중")
    @RequestMapping(value = "/statusReserved", method = RequestMethod.POST)
    public SuccessResponse updateStatusReserved(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                @RequestParam Long postId) {
        return SuccessResponse.success(tradeService.updateStatusReserved(userDetails.getUser(), postId));
    }

    @Operation(summary = "중고거래 게시물 예약 완료")
    @RequestMapping(value = "/statusMatched", method = RequestMethod.POST)
    public SuccessResponse updateStatusMatched(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @RequestParam Long postId) {
        return SuccessResponse.success(tradeService.updateStatusMatched(userDetails.getUser(), postId));
    }

}