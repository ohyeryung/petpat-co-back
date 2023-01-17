package com.smile.petpat.post.rehoming.domain;

import com.smile.petpat.config.comm.Timestamped;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.status.PostStatus;
import com.smile.petpat.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_REHOMING")
@Builder
@DynamicUpdate
public class Rehoming extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REHOMING_ID")
    private Long rehomingId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "PET_NAME")
    private String petName;

    @Column(name = "PET_AGE")
    private String petAge;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Column(name = "POST_TYPE")
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @Column(name = "VIEW_CNT")
    private int viewCnt;

public Rehoming(Long rehomingId, User user, String title, String description, String petName, String petAge,
                String category, String type, String gender, String location, Long price, PostStatus status,
                PostType postType, int viewCnt) {
    this.rehomingId = rehomingId;
    this.user = user;
    this.title = title;
    this.description = description;
    this.petName = petName;
    this.petAge = petAge;
    this.category = category;
    this.type = type;
    this.gender = gender;
    this.location = location;
    this.price = price;
    this.status = status;
    this.postType = postType;
    this.viewCnt = viewCnt;
}
    public Rehoming() {

    }

    // 분양 게시글 수정
public void update(Rehoming initRehoming) {
    this.title = initRehoming.getTitle();
    this.description = initRehoming.getDescription();
    this.petName = initRehoming.getPetName();
    this.petAge = initRehoming.getPetAge();
    this.category = initRehoming.getCategory();
    this.type = initRehoming.getType();
    this.gender = initRehoming.getGender();
    this.location = initRehoming.getLocation();
    this.price = initRehoming.getPrice();
    this.status = initRehoming.getStatus();
    this.postType = PostType.REHOMING;
}
}


