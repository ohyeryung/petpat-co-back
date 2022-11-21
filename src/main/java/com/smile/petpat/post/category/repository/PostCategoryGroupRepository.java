package com.smile.petpat.post.category.repository;

import com.smile.petpat.post.category.domain.CategoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCategoryGroupRepository extends JpaRepository<CategoryGroup,Long> {
}
