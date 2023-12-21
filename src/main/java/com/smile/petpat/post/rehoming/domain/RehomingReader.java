package com.smile.petpat.post.rehoming.domain;

import com.smile.petpat.post.category.domain.CategoryGroup;
import com.smile.petpat.post.category.domain.PetCategory;

public interface RehomingReader {
    Rehoming readRehomingById(Long rehomingId);

    void userChk(String userEmail, Rehoming rehoming);

    CategoryGroup readCategoryById(Long categoryId);

    PetCategory readPetTypeById(Long petCategoryId);
}
