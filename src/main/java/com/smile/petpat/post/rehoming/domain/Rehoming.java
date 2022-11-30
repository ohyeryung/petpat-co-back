package com.smile.petpat.post.rehoming.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.rehoming.dto.RehomingDto;
import com.smile.petpat.config.comm.Timestamped;
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
    @Column(name = "rehomingPostId")
    private Long rehomingPostId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

//    @Column(name = "viewCnt")
//    private int viewCnt;

    @Column(name = "petName")
    private String petName;

    @Column(name = "petAge")
    private String petAge;

    @Column(name = "category")
    private String category;

    @Column(name = "type")
    private String type;

    @Column(name = "gender")
    private String gender;

    @Column(name = "region")
    private String region;

    @Column(name = "price")
    private int price;

//    @Column(name = "isCompleted")
//    private boolean isCompleted;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "post_type")
    @Enumerated(EnumType.STRING)
    private PostType postType;

    public Rehoming(User user, RehomingDto rehomingDto) {
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
        this.status = Status.RESERVING;
    }

    public Rehoming(RehomingDto rehomingDto) {
        super();
    }
}


