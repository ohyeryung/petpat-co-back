package com.smile.petpat.post.rehoming.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.status.PostStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RehomingInfo {
    private Long rehomingId;
    private String rehomingImg;
    private Long userId;
    private String nickname;
    private String title;
    private String description;
    private String petName;
    private String petAge;
    private String category;
    private String type;
    private String gender;
    private String region;
    private Long price;
    private PostStatus status;
    private PostType postType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isLiked;
    private boolean isBookmarked;
    private int veiwCnt;
    private int likeCnt;
    private int bookmarkCnt;

    public RehomingInfo(Rehoming rehoming) {
        this.rehomingId = rehoming.getRehomingId();
        // this.rehomingImg = rehomingImg;
        this.userId = rehoming.getUser().getId();
        this.nickname = rehoming.getUser().getNickname();
        this.title = rehoming.getTitle();
        this.description = rehoming.getDescription();
        this.petName = rehoming.getPetName();
        this.petAge = rehoming.getPetAge();
        this.category = rehoming.getCategory();
        this.type = rehoming.getType();
        this.gender = rehoming.getGender();
        this.region = rehoming.getRegion();
        this.price = rehoming.getPrice();
        this.status = rehoming.getStatus();
        this.postType = rehoming.getPostType();
        this.createdAt = rehoming.getCreatedAt();
        this.updatedAt = rehoming.getUpdatedAt();
        this.isLiked = false;
        this.isBookmarked = false;
        this.veiwCnt = rehoming.getViewCnt();
        this.likeCnt = 0;
        this.bookmarkCnt = 0;
    }
}
