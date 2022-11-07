package com.smile.petpat.category.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class PostCategory {

    @Id
    private Long postCategoryId;

    @Column
    private String postCategoryName;

    @ManyToOne
    @JoinColumn(referencedColumnName = "")
    private PostCategoryGroup postCategoryGroup;

    public PostCategory(){
    }

    public PostCategory(Long postCategoryId, String postCategoryName, PostCategoryGroup postCategoryGroup) {
        this.postCategoryId = postCategoryId;
        this.postCategoryName = postCategoryName;
        this.postCategoryGroup = postCategoryGroup;
    }
}
