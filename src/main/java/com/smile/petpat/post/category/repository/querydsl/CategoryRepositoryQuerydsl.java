package com.smile.petpat.post.category.repository.querydsl;

import com.smile.petpat.post.category.dto.PostCategoryDto;

import java.util.List;

public interface CategoryRepositoryQuerydsl {
    List<PostCategoryDto.TradeCategoryDetailResponse> getTradeCategoryAndCnt(Long tradeCategoryId);
}
