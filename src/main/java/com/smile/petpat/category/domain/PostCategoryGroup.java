package com.smile.petpat.category.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "postCategoryGroup")
public class PostCategoryGroup {
    @Id
    private Long postCategoryGroupId;

    @Column
    private String postCategoryGroupName;

    @Column
    @Enumerated(EnumType.STRING)
    private PostType postType;

    public PostCategoryGroup(){
    }

    public PostCategoryGroup(Long postCategoryGroupId, String postCategoryGroupName, PostType postType) {
        this.postCategoryGroupId = postCategoryGroupId;
        this.postCategoryGroupName = postCategoryGroupName;
        this.postType = postType;
    }
}
