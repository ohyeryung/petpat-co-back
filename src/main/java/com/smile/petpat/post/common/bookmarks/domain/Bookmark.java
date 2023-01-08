package com.smile.petpat.post.common.bookmarks.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.user.domain.User;

import javax.persistence.*;

@Table
@Entity(name = "TB_BOOKMARK")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKMARK_ID")
    private Long bookmarkId;

    @Column(name = "POST_TYPE")
    @Enumerated(EnumType.STRING)
    private PostType postType;

    @Column(name = "POST_ID")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;

    public Bookmark(){

    }

    public Bookmark(PostType postType, Long postId, User user) {
        this.postType = postType;
        this.postId = postId;
        this.user = user;
    }
}

