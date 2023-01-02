package com.smile.petpat.post.qna.domain;

import com.smile.petpat.user.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "TB_COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long commentId;

    @Column(name = "PARENT_COMMENT_ID")
    private Long parentCommentId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "USER_ID",name = "USER_ID")
    private User user;


    @Column(name = "CONTENT")
    private String content;

    public Comment() {
    }

    public Comment(Long commentId, Long parentCommentId, User user, String content) {
        this.commentId = commentId;
        this.parentCommentId = parentCommentId;
        this.user = user;
        this.content = content;
    }
}
