package com.smile.petpat.post.category.repository;

import com.smile.petpat.post.category.domain.CategoryGroup;
import com.smile.petpat.post.category.domain.TradeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TradeCategoryRepository extends JpaRepository<TradeCategory,Long> {
    List<TradeCategory> findAllByCategoryGroup(CategoryGroup categoryGroup);
    Optional<TradeCategory> findByTradeCategoryId(Long tradeCategoryId);
}
