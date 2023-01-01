package com.smile.petpat.post.common.likes.domain;

import com.smile.petpat.post.category.domain.PostType;

import javax.persistence.*;

@Table
@Entity(name = "TB_LIKES")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID")
    private Long likeId;

    @Column(name = "POST_TYPE")
    @Enumerated(EnumType.STRING)
    private PostType postType;

    @Column(name = "POST_ID")
    private Long postId;

    public Likes(){

    }
    public Likes(Long likeId, PostType postType, Long postId) {
        this.likeId = likeId;
        this.postType = postType;
        this.postId = postId;
    }
}
