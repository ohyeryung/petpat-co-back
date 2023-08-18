package com.smile.petpat.post.category.repository.querydsl;

import com.smile.petpat.post.category.dto.PostCategoryInfo;

import java.util.List;

public interface CategoryRepositoryQuerydsl {
    List<PostCategoryInfo.TradeCategoryDetailResponse> getTradeCategoryAndCnt(Long tradeCategoryId);
}
