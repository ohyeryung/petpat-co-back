package com.smile.petpat.post.category.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TB_CATEGORY_GROUP")
public class CategoryGroup {
    @Id
    @Column(name = "CATEGORY_GROUP_ID")
    private Long categoryGroupId;

    @Column(name = "CATEGORY_GROUP_NAME")
    private String categoryGroupName;

    @Column(name = "POST_TYPE")
    @Enumerated(EnumType.STRING)
    private PostType postType;

    public CategoryGroup(){
    }

    public CategoryGroup(Long categoryGroupId, String categoryGroupName, PostType postType) {
        this.categoryGroupId = categoryGroupId;
        this.categoryGroupName = categoryGroupName;
        this.postType = postType;
    }
}
