package com.smile.petpat.post.trade.service;

import com.smile.petpat.post.trade.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService{

    private final TradeStore  tradeStore;
    private final TradeReader tradeReader;

    @Override
    @Transactional
    public TradeInfo registerTrade(TradeCommand tradeCommand) {
        //1. 게시물 등록
        Trade initTrade = tradeCommand.toEntity();
        Trade trade= tradeStore.store(initTrade);
        return new TradeInfo(trade);
        //2. 사진 등록
    }
}
