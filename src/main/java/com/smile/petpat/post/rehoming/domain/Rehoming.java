package com.smile.petpat.post.rehoming.domain;

import com.smile.petpat.config.comm.Timestamped;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.status.PostStatus;
import com.smile.petpat.post.rehoming.dto.RehomingReqDto;
import com.smile.petpat.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_REHOMING")
public class Rehoming  extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REHOMING_ID")
    private Long rehomingId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT", nullable = true)
    private String description;

    @Column(name = "VIEW_CNT")
    private int viewCnt;

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

    @Column(name = "REGION")
    private String region;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Column(name = "POST_TYPE")
    @Enumerated(EnumType.STRING)
    private PostType postType;

    public Rehoming(User user, RehomingReqDto rehomingDto) {
        this.user = user;
        this.title = rehomingDto.getTitle();
        this.description = rehomingDto.getDescription();
        this.petName = rehomingDto.getPetName();
        this.petAge = rehomingDto.getPetAge();
        this.category = rehomingDto.getCategory();
        this.type = rehomingDto.getType();
        this.gender = rehomingDto.getGender();
        this.region = rehomingDto.getRegion();
        this.price = rehomingDto.getPrice();
        // default 값으로 예약 중 data 입력 후 상태값 변경에 따라 전환
        this.status = PostStatus.REHOMING_RESERVING;
        this.postType = PostType.REHOMING;
    }
}


