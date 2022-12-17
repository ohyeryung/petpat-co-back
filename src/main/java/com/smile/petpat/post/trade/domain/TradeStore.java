package com.smile.petpat.post.trade.domain;

public interface TradeStore {
    Trade store(Trade trade);
    void delete(Long tradeId, Long userId);
    Trade patch(Trade trade,Long userId);
}
