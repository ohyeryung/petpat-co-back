package com.smile.petpat.post.trade.controller;

import com.smile.petpat.common.response.SuccessResponse;
import com.smile.petpat.post.trade.domain.TradeCommand;
import com.smile.petpat.post.trade.domain.TradeDto;
import com.smile.petpat.post.trade.domain.TradeInfo;
import com.smile.petpat.post.trade.service.TradeServiceImpl;
import com.smile.petpat.user.service.UserDetailsImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"post_trade_api"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trade")
public class TradeController {

    private final TradeServiceImpl tradeService;

    /**
     * 분양게시물 등록
     * @return 성공 시 200 Success 반환
     */
    @ApiOperation(value = "분양게시물 등록", notes = "분양게시물 등록")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public SuccessResponse registerTrade(@RequestBody @Valid TradeDto.CommonTrade tradeDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        TradeCommand tradeCommand = tradeDto.toCommand();
        tradeService.registerTrade(tradeCommand,userDetails.getUser());
        return SuccessResponse.success("ok");
    }

    /**
     * 분양게시물 목록 조회
     * @return 성공 시 200 Success 와 함께 분양게시물 목록 반환
     */
    @ApiOperation(value = "분양게시물 목록 조회", notes = "분양 게시물 목록 조회")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public SuccessResponse listTrade(){
       List<TradeInfo> tradeInfos =  tradeService.listTrade();
       return SuccessResponse.success(tradeInfos,"ok");
    }
    /**
     * 분양게시물 수정
     * @return 성공 시 200 Success 와 함께 수정한 분양게시물  반환
     */
    @ApiOperation(value = "분양게시물 수정", notes = "분양 게시물 수정")
    @RequestMapping(value = "",method = RequestMethod.DELETE)
    public SuccessResponse updateTrade(@RequestBody TradeDto.CommonTrade updateTrade,@AuthenticationPrincipal UserDetailsImpl userDetails){
        TradeCommand tradeCommand = updateTrade.toCommand();
        return SuccessResponse.success(tradeService.updateTrade(tradeCommand,userDetails.getUser()));

    }

    /**
     * 분양게시물  삭제
     * @return 성공 시 200 Success 반환
     */
    @ApiOperation(value = "분양게시물 삭제", notes = "분양 게시물 삭제")
    @RequestMapping(value = "/{tradeId}",method = RequestMethod.DELETE)
    public SuccessResponse deleteTrade(@PathVariable Long tradeId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        tradeService.deleteTrade(tradeId,userDetails.getUser());
        return SuccessResponse.success("ok");
    }
}
