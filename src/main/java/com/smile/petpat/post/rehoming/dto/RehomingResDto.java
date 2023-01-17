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
    private String location;
    private Long price;
    private PostStatus status;
    private PostType postType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isLiked;
    private boolean isBookmarked;
    private int viewCnt;
    private Long likeCnt;
    private Long bookmarkCnt;


    // 회원 조회 시
    public RehomingResDto(Rehoming rehoming, List<String> rehomingImg, boolean isLiked, boolean isBookmarked, Long likeCnt, Long bookmarkCnt) {
        this.rehomingId = rehoming.getRehomingId();
        this.userId = rehoming.getUser().getId();
        this.nickname = rehoming.getUser().getNickname();
        this.title = rehoming.getTitle();
        this.description = rehoming.getDescription();
        this.petName = rehoming.getPetName();
        this.petAge = rehoming.getPetAge();
        this.category = rehoming.getCategory().getCategoryGroupName();
        this.type = rehoming.getType().getPetCategoryName();
        this.gender = rehoming.getGender();
        this.location = rehoming.getLocation();
        this.price = rehoming.getPrice();
        this.status = rehoming.getStatus();
        this.rehomingImg = rehomingImg;
        this.postType = rehoming.getPostType();
        this.createdAt = rehoming.getCreatedAt();
        this.updatedAt = rehoming.getUpdatedAt();
        this.isLiked = isLiked;
        this.isBookmarked = isBookmarked;
        this.viewCnt = rehoming.getViewCnt();
        this.likeCnt = likeCnt;
        this.bookmarkCnt = bookmarkCnt;
    }

    // 비회원 조회 시
    public RehomingResDto(Rehoming rehoming, List<String> rehomingImg, Long likeCnt, Long bookmarkCnt) {
        this.rehomingId = rehoming.getRehomingId();
        this.userId = rehoming.getUser().getId();
        this.nickname = rehoming.getUser().getNickname();
        this.title = rehoming.getTitle();
        this.description = rehoming.getDescription();
        this.petName = rehoming.getPetName();
        this.petAge = rehoming.getPetAge();
        this.category = rehoming.getCategory().getCategoryGroupName();
        this.type = rehoming.getType().getPetCategoryName();
        this.gender = rehoming.getGender();
        this.location = rehoming.getLocation();
        this.price = rehoming.getPrice();
        this.status = rehoming.getStatus();
        this.rehomingImg = rehomingImg;
        this.postType = rehoming.getPostType();
        this.createdAt = rehoming.getCreatedAt();
        this.updatedAt = rehoming.getUpdatedAt();
        this.isLiked = false;
        this.isBookmarked = false;
        this.viewCnt = rehoming.getViewCnt();
        this.likeCnt = likeCnt;
        this.bookmarkCnt = bookmarkCnt;
    }
}
