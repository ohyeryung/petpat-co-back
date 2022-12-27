package com.smile.petpat.post.rehoming.dto;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.status.PostStatus;
import com.smile.petpat.post.rehoming.domain.Rehoming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class RehomingResDto {
    private Long rehomingId;
    private Long userId;
    private String nickname;
    private List<String> rehomingImg;
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
    private List<String> tagList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private boolean isLiked;
    private boolean isBookmarked;
    private int viewCnt;
    private int likeCnt;
    private int bookmarkCnt;

public RehomingResDto(Rehoming rehoming, List<String> rehomingImg) {
    this.rehomingId = rehoming.getRehomingId();
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
    this.rehomingImg = rehomingImg;
    // this.tagList = tagList;
    this.postType = rehoming.getPostType();
    this.createdAt = rehoming.getCreatedAt();
    this.updatedAt = rehoming.getUpdatedAt();
    this.isLiked = false;
    this.isBookmarked = false;
    this.viewCnt = rehoming.getViewCnt();
    this.likeCnt = 0;
    this.bookmarkCnt = 0;
}
}
