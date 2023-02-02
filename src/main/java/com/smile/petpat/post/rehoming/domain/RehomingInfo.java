package com.smile.petpat.post.rehoming.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.status.PostStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
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
    private String cityName;
    private String cityCountryName;
    private String townShipName;
    private String detailAdName;
    private String fullAdName;
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

    // 회원
    public RehomingInfo(Long rehomingId, Long userId, String nickname,
                        String title, String description, String petName, String petAge,
                        String category, String type, String gender, String cityName, String cityCountryName,
                        String townShipName, String detailAdName, String fullAdName,
                        Long price, PostStatus status, PostType postType, LocalDateTime createdAt, LocalDateTime updatedAt,
                        Long isLiked, Long isBookmarked, int viewCnt, Long likeCnt, Long bookmarkCnt) {
        this.rehomingId = rehomingId;
        this.userId = userId;
        this.nickname = nickname;
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
        // this.rehomingImg = rehomingImg;
        this.postType = postType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isLiked = booleanChk(isLiked);
        this.isBookmarked = booleanChk(isBookmarked);
        this.viewCnt = viewCnt;
        this.likeCnt = likeCnt;
        this.bookmarkCnt = bookmarkCnt;
    }

    private Boolean booleanChk(Long chkValue) {
        return chkValue != 0;
    }

    // 비회원
    public RehomingInfo(Long rehomingId, Long userId, String nickname,
                        String title, String description, String petName, String petAge,
                        String category, String type, String gender, String cityName, String cityCountryName,
                        String townShipName, String detailAdName, String fullAdName,
                        Long price, PostStatus status, PostType postType, LocalDateTime createdAt, LocalDateTime updatedAt,
                        int viewCnt, Long likeCnt, Long bookmarkCnt) {
        this.rehomingId = rehomingId;
        this.userId = userId;
        this.nickname = nickname;
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
        // this.rehomingImg = rehomingImg;
        this.postType = postType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isLiked = false;
        this.isBookmarked = false;
        this.viewCnt = viewCnt;
        this.likeCnt = likeCnt;
        this.bookmarkCnt = bookmarkCnt;
    }
}
