package com.smile.petpat.post.category.repository;

import com.smile.petpat.post.category.domain.TradeCategory;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TradeCategoryDetailRepository extends JpaRepository<TradeCategoryDetail,Long> {
    List<TradeCategoryDetail> findAllByTradeCategory(TradeCategory tradeCategory);

    Optional<TradeCategoryDetail> findByTradeCategoryDetailId(Long tradeCategoryDetailId);
}
