package com.smile.petpat.post.trade.service;

import com.smile.petpat.post.trade.domain.TradeReader;
import com.smile.petpat.post.trade.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TradeReaderImpl implements TradeReader {
    private final TradeRepository tradeRepository;
}
