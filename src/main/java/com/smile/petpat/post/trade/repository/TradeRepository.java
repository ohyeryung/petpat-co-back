package com.smile.petpat.post.trade.repository;

import com.smile.petpat.post.trade.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface TradeRepository extends JpaRepository<Trade,Long> {

    @Transactional
    @Modifying
    @Query("update Trade set viewCnt = viewCnt + 1 where tradeId = :postId")
    void updateViews(Long postId);

}
