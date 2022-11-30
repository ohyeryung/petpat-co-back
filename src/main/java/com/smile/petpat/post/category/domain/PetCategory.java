package com.smile.petpat.post.category.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TB_PET_CATEGORY")
public class PetCategory {

    @Id
    @Column(name = "PET_CATEGORY_ID")
    private Long PetCategoryId;

    @Column(name = "PET_CATEGORY_NAME")
    private String postCategoryName;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_GROUP",referencedColumnName = "")
    private CategoryGroup categoryGroup;

    public PetCategory(){
    }

    public PetCategory(Long petCategoryId, String postCategoryName, CategoryGroup categoryGroup) {
        PetCategoryId = petCategoryId;
        this.postCategoryName = postCategoryName;
        this.categoryGroup = categoryGroup;
    }
}
