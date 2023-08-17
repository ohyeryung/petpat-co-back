package com.smile.petpat.post.qna.domain;


import javax.persistence.*;

import com.smile.petpat.config.comm.Timestamped;
import com.smile.petpat.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Reference;

import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "TB_QNA")
@Entity
public class Qna extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QNA_ID")
    private Long qnaId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;
    @ManyToOne
    @JoinColumn(referencedColumnName = "USER_ID",name = "USER_ID")
    private User user;

    @Column(name = "VIEW_CNT")
    private Long viewCnt;

    @OneToMany(mappedBy = "qna", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Qna() {
    }

    public Qna(Long qnaId, String title, String content, User user, Long viewCnt) {
        this.qnaId = qnaId;
        this.title = title;
        this.content = content;
        this.user = user;
        this.viewCnt = viewCnt;
    }
    @Builder
    public Qna(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
