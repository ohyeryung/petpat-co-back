package com.smile.petpat.post.trade.repository;

import com.smile.petpat.post.trade.domain.Trade;
import com.smile.petpat.post.trade.repository.querydsl.TradeRepositoryQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade,Long>, TradeRepositoryQueryDsl {
}
