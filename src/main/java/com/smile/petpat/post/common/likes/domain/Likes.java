package com.smile.petpat.post.common.likes.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.user.domain.User;

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

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;

    public Likes(){

    }

    public Likes(PostType postType, Long postId, User user) {
        this.postType = postType;
        this.postId = postId;
        this.user = user;
    }
}
