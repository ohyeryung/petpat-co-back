package com.smile.petpat.post.category.repository;

import com.smile.petpat.post.category.domain.CategoryGroup;
import com.smile.petpat.post.category.domain.PetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetCategoryRepository extends JpaRepository<PetCategory,Long> {
    List<PetCategory> findAllByCategoryGroup(CategoryGroup CategoryGroup);
}
