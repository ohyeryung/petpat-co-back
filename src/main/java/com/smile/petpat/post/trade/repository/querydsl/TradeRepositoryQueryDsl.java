package com.smile.petpat.post.trade.repository.querydsl;

import com.smile.petpat.post.trade.domain.TradeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TradeRepositoryQueryDsl {
    List<TradeInfo.TradeList> tradeList(Long userId);

    Page<TradeInfo.TradeList> tradeList_Paging(Long userId, Pageable pageable);

    TradeInfo.TradeDetail tradeDetail(Long userId, Long tradeId);
}
