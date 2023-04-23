package com.smile.petpat.post.rehoming.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.status.PostStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class RehomingInfo {
    private Long rehomingId;
    private String rehomingImg;
    private Long userId;
    private String nickname;
    private String title;
    private String petName;
    private String category;
    private String type;
    private String gender;
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
    public RehomingInfo(Long rehomingId, String rehomingImg, Long userId, String nickname,
                        String title, String petName,
                        String category, String type, String gender,
                        Long price, PostStatus status, PostType postType, LocalDateTime createdAt, LocalDateTime updatedAt,
                        Long isLiked, Long isBookmarked, int viewCnt, Long likeCnt, Long bookmarkCnt) {
        this.rehomingId = rehomingId;
        this.rehomingImg = rehomingImg;
        this.userId = userId;
        this.nickname = nickname;
        this.title = title;
        this.petName = petName;
        this.category = category;
        this.type = type;
        this.gender = gender;
        this.price = price;
        this.status = status;
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
    public RehomingInfo(Long rehomingId, String rehomingImg, Long userId, String nickname,
                        String title, String petName,
                        String category, String type, String gender,
                        Long price, PostStatus status, PostType postType, LocalDateTime createdAt, LocalDateTime updatedAt,
                        int viewCnt, Long likeCnt, Long bookmarkCnt) {
        this.rehomingId = rehomingId;
        this.rehomingImg = rehomingImg;
        this.userId = userId;
        this.nickname = nickname;
        this.title = title;
        this.petName = petName;
        this.category = category;
        this.type = type;
        this.gender = gender;
        this.price = price;
        this.status = status;
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
