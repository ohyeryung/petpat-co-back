package com.smile.petpat.post.trade.service;

import com.smile.petpat.post.trade.domain.TradeCommand;
import com.smile.petpat.post.trade.domain.TradeInfo;

public interface TradeService{

    TradeInfo registerTrade(TradeCommand tradeCommand);
}
