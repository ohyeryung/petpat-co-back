package com.smile.petpat.post.trade.service;

import com.smile.petpat.post.trade.domain.TradeCommand;
import com.smile.petpat.post.trade.domain.TradeInfo;
import com.smile.petpat.user.domain.User;

import java.util.List;

public interface TradeService{

    void registerTrade(TradeCommand tradeCommand, User user);

    List<TradeInfo> listTrade();
}
