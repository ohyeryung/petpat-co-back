package com.smile.petpat.post.category.service;

import com.smile.petpat.post.category.domain.CategoryGroup;

import java.util.List;

public interface PostCategoryService {
    List<CategoryGroup> getCategoryGroup(String postTypeDescription);
}
