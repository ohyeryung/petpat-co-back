package com.smile.petpat.trade.repository;

import com.smile.petpat.trade.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade,Long> {
}
