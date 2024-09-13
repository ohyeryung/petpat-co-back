package com.smile.petpat.post.qna.domain;

import com.smile.petpat.config.comm.Timestamped;
import com.smile.petpat.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TB_COMMENT")
public class Comment extends Timestamped {

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

    @ManyToOne
    @JoinColumn(name = "qna_id")
    private Qna qna;

    public Comment() {
    }

    public Comment(Long commentId, Long parentCommentId, User user, String content) {
        this.commentId = commentId;
        this.parentCommentId = parentCommentId;
        this.user = user;
        this.content = content;
    }
}
