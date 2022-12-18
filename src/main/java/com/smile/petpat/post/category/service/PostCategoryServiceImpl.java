package com.smile.petpat.post.category.service;

import com.smile.petpat.post.category.domain.CategoryGroup;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.repository.PostCategoryGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCategoryServiceImpl implements PostCategoryService{

    private final PostCategoryGroupRepository postCategoryGroupRepository;

    @Override
    public List<CategoryGroup> getCategoryGroup(String postTypeDescription) {
        PostType postType = PostType.find(postTypeDescription);
        return postCategoryGroupRepository.findAllByPostType(postType);

    }
}
