package com.smile.petpat.post.category.repository;

import com.smile.petpat.post.category.domain.CategoryGroup;
import com.smile.petpat.post.category.domain.PostType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCategoryGroupRepository extends JpaRepository<CategoryGroup,Long> {

    List<CategoryGroup> findAllByPostType(PostType postType);
}
