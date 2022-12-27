package com.smile.petpat.post.rehoming.dto;

import lombok.Getter;

@Getter
public class RehomingUpdateReqDto {
    private Long rehomingId;
    private String title;
    private String description;
    private String petName;
    private String petAge;
    private String category;
    private String type;
    private String gender;
    private String region;
    private int price;
    private boolean isCompleted;
}
