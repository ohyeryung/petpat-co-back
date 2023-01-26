package com.smile.petpat.post.category.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TB_PET_CATEGORY")
public class PetCategory {

    @Id
    @Column(name = "PET_CATEGORY_ID")
    private Long petCategoryId;

    @Column(name = "PET_CATEGORY_NAME")
    private String petCategoryName;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_GROUP_ID", referencedColumnName = "")
    private CategoryGroup categoryGroup;

    public PetCategory(){
    }

    public PetCategory(Long petCategoryId, String petCategoryName, CategoryGroup categoryGroup) {
        this.petCategoryId = petCategoryId;
        this.petCategoryName = petCategoryName;
        this.categoryGroup = categoryGroup;
    }
}
