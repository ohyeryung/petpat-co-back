package com.smile.petpat.post.trade.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Trades {

    private final List<Trade> tradeList;

    public Trades(List<Trade> tradeList) {
        this.tradeList = tradeList;
    }

    public List<TradeInfo> tradeToTradeInfo(List<Trade> trades){
        return trades.stream()
                .map(TradeInfo::new).collect(Collectors.toList());
    }
}
