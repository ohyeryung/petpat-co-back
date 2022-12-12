package com.smile.petpat.post.trade.service;

import com.smile.petpat.post.trade.domain.TradeCommand;
import com.smile.petpat.post.trade.domain.TradeInfo;

import java.util.List;

public interface TradeService{

    TradeInfo registerTrade(TradeCommand tradeCommand);

    List<TradeInfo> listTrade();
}
