package com.smile.petpat.post.trade.domain;

public interface TradeStore {
    Trade store(Trade initTrade);
    void delete(Long tradeId, Long userId);
}
