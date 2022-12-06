package com.smile.petpat.post.trade.controller;

import com.smile.petpat.post.trade.domain.TradeCommand;
import com.smile.petpat.post.trade.domain.TradeDto;
import com.smile.petpat.post.trade.domain.TradeInfo;
import com.smile.petpat.post.trade.service.TradeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trade")
public class TradeController {

    private final TradeServiceImpl tradeService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void registerTrade(@RequestBody @Valid TradeDto.RegisterTrade tradeDto){
        TradeCommand tradeCommand = tradeDto.toCommand();
        TradeInfo tradeInfo = tradeService.registerTrade(tradeCommand);
    }
}
