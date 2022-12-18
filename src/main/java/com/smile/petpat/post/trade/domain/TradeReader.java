package com.smile.petpat.post.trade.domain;

import java.util.List;

public interface TradeReader {
    List<Trade> readTradeList();
    Trade readTradeById(Long tradeId);
}
