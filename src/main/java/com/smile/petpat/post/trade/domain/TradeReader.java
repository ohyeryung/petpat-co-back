package com.smile.petpat.post.trade.domain;

import com.smile.petpat.post.category.domain.TradeCategoryDetail;

import java.util.List;

public interface TradeReader {
    List<Trade> readTradeList();
    Trade readTradeById(Long tradeId);
    TradeCategoryDetail readTradeCategoryDetailById(Long tradeCategoryDetailId);
}
