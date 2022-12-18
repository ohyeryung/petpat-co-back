package com.smile.petpat.tag.domain;

import com.smile.petpat.post.category.domain.PostGroup;
import com.smile.petpat.post.rehoming.domain.Rehoming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_TAG")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID")
    private Long tagId;

    @Column(name = "TAG")
    private String tag;

    @Enumerated(EnumType.STRING)
    @Column(name = "POST_TYPE")
    private PostGroup postType;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Rehoming rehoming;

}
