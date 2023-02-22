package com.smile.petpat.post.rehoming.domain;

import com.smile.petpat.config.comm.Timestamped;
import com.smile.petpat.post.category.domain.CategoryGroup;
import com.smile.petpat.post.category.domain.PetCategory;
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

    @Column(name = "TITLE", nullable = false, length = 20)
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT", nullable = false, length = 2000)
    private String description;

    @Column(name = "PET_NAME", nullable = false)
    private String petName;

    @Column(name = "PET_AGE", nullable = false)
    private String petAge;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_GROUP_ID", nullable = false)
    private CategoryGroup category;

    @ManyToOne
    @JoinColumn(name = "PET_CATEGORY_ID", nullable = false)
    private PetCategory type;

    @Column(name = "GENDER", nullable = false)
    private String gender;

    @Column(name = "CITY_NAME", nullable = false)
    private String cityName;

    @Column(name = "CITY_COUNTRY_NAME", nullable = false)
    private String cityCountryName;

    @Column(name = "TOWNSHIP_NAME", nullable = false)
    private String townShipName;

    @Column(name = "DETAIL_AD_NAME")
    private String detailAdName;

    @Column(name = "FULL_AD_NAME", nullable = false)
    private String fullAdName;

    @Column(name = "PRICE", length = 8)
    private Long price;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Column(name = "POST_TYPE")
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @Column(name = "VIEW_CNT")
    private int viewCnt;

    public void isFinding() {
        this.status = PostStatus.REHOMING_FINDING;
    }

    public void isReserved() {
        this.status = PostStatus.REHOMING_RESERVING;
    }

    public void isMatched() {
        this.status = PostStatus.REHOMING_MATCHED;
    }

    public Rehoming(Long rehomingId, User user, String title, String description, String petName, String petAge,
                    CategoryGroup category, PetCategory type, String gender, String cityName,
                    String cityCountryName, String townShipName, String detailAdName, String fullAdName,
                    Long price, PostStatus status, PostType postType, int viewCnt) {
        this.rehomingId = rehomingId;
        this.user = user;
        this.title = title;
        this.description = description;
        this.petName = petName;
        this.petAge = petAge;
        this.category = category;
        this.type = type;
        this.gender = gender;
        this.cityName = cityName;
        this.cityCountryName = cityCountryName;
        this.townShipName = townShipName;
        this.detailAdName = detailAdName;
        this.fullAdName = fullAdName;
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
        this.cityName = initRehoming.getCityName();
        this.cityCountryName = initRehoming.getCityCountryName();
        this.townShipName = initRehoming.getTownShipName();
        this.detailAdName = initRehoming.getDetailAdName();
        this.fullAdName = initRehoming.getFullAdName();
        this.price = initRehoming.getPrice();
        this.status = initRehoming.getStatus();
    }

    // 조회수 증가
    public void updateViewCnt(Rehoming rehoming) {
        this.viewCnt = rehoming.getViewCnt() + 1;
    }

}


