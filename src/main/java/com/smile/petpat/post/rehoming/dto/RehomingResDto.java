package com.smile.petpat.post.rehoming.dto;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.status.PostStatus;
import com.smile.petpat.post.rehoming.domain.Rehoming;
import com.smile.petpat.post.rehoming.domain.RehomingCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private LocalDate petAge;
    private String category;
    private String type;
    private RehomingCommand.PetGender gender;
    private String cityName;
    private String cityCountryName;
    private String townShipName;
    private String detailAdName;
    private String fullAdName;
    private PostStatus status;
    private PostType postType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isLiked;
    private boolean isBookmarked;
    private int viewCnt;
    private int likeCnt;
    private int bookmarkCnt;


    // 회원 조회 시
    public RehomingResDto(Rehoming rehoming, List<String> rehomingImg, boolean isLiked, boolean isBookmarked, int likeCnt, int bookmarkCnt) {
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
        this.cityName = rehoming.getCityName();
        this.cityCountryName = rehoming.getCityCountryName();
        this.townShipName = rehoming.getTownShipName();
        this.detailAdName = rehoming.getDetailAdName();
        this.fullAdName = rehoming.getFullAdName();
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
    public RehomingResDto(Rehoming rehoming, List<String> rehomingImg, int likeCnt, int bookmarkCnt) {
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
        this.cityName = rehoming.getCityName();
        this.cityCountryName = rehoming.getCityCountryName();
        this.townShipName = rehoming.getTownShipName();
        this.detailAdName = rehoming.getDetailAdName();
        this.fullAdName = rehoming.getFullAdName();
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
