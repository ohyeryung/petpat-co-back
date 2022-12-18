package com.smile.petpat.post.trade.service;

import com.smile.petpat.post.trade.domain.Trade;
import com.smile.petpat.post.trade.domain.TradeReader;
import com.smile.petpat.post.trade.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class TradeReaderImpl implements TradeReader {
    private final TradeRepository tradeRepository;

    @Override
    public List<Trade> readTradeList() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade readTradeById(Long tradeId) {
        return   tradeRepository.findById(tradeId).orElseThrow(
                ()  -> new IllegalArgumentException("존재하지 않는 게시물입니다.")
        );
    }

    public void userChk(Long tradeId,Long userId){
       Trade trade = readTradeById(tradeId);
       log.info("게시물의 유저아이디와 수정하는 아이디 ={}{}",trade.getUser().getId().equals(userId),userId);
       if(!trade.getUser().getId().equals(userId)) {
           throw new IllegalArgumentException("권한이 없습니다.");
       }

    }
}
