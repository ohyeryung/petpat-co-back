package com.smile.petpat.post.category.repository;

import com.smile.petpat.post.category.domain.TradeCategory;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeCategoryDetailRepository extends JpaRepository<TradeCategoryDetail,Long> {
    List<TradeCategoryDetail> findAllByTradeCategory(TradeCategory tradeCategory);
}
