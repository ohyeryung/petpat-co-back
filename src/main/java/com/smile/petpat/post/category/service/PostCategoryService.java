package com.smile.petpat.post.category.service;

import com.smile.petpat.post.category.domain.CategoryGroup;
import com.smile.petpat.post.category.domain.TradeCategory;

import java.util.List;

import static com.smile.petpat.post.category.dto.PostCategoryInfo.*;

public interface PostCategoryService {
    List<CategoryGroup> getCategoryGroup(String postTypeDescription);
    List<PetCategoryRes> getPetCategory(Long categoryGroup);
    List<TradeCategoryRes> getTradeCategory(Long categoryGroup);
}
