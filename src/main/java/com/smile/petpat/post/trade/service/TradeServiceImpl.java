package com.smile.petpat.post.trade.service;

import com.smile.petpat.post.trade.domain.*;
import com.smile.petpat.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService{

    private final TradeStore  tradeStore;
    private final TradeReader tradeReader;

    @Override
    @Transactional
    public void registerTrade(TradeCommand tradeCommand, User user) {
        //1. 게시물 등록
        Trade initTrade = tradeCommand.toRegisterEntity(user);
        Trade trade= tradeStore.store(initTrade);
        //2. 사진 등록
    }

    @Override
    @Transactional
    public List<TradeInfo> listTrade() {
        List<Trade> listTrade = tradeReader.readTradeList();
        List<TradeInfo> tradeInfos = listTrade.stream()
                .map(TradeInfo::new).collect(Collectors.toList());
        return tradeInfos;
    }
    @Override
    @Transactional
    public void deleteTrade(Long tradeId, User user) {
        tradeStore.delete(tradeId, user.getId());
    }

    @Override
    @Transactional
    public TradeInfo updateTrade(TradeCommand tradeCommand, User user,Long tradeId) {
        Trade initTrade = tradeCommand.toUpdateEntity(user,tradeId);
        Trade trade = tradeStore.update(initTrade,user.getId(),tradeId);
        TradeInfo tradeInfo = new TradeInfo(trade);
        return tradeInfo;
    }


}
