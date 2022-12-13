package com.smile.petpat.post.trade.service;

import com.smile.petpat.post.trade.domain.Trade;
import com.smile.petpat.post.trade.domain.TradeReader;
import com.smile.petpat.post.trade.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TradeReaderImpl implements TradeReader {
    private final TradeRepository tradeRepository;

    @Override
    public List<Trade> readTradeList() {
        return tradeRepository.findAll();
    }
}
