package com.smile.petpat.post.rehoming.dto;

import com.smile.petpat.post.rehoming.domain.Rehoming;
import com.smile.petpat.post.rehoming.domain.Status;

import java.util.List;

public class RehomingResponseDto {
    private Long rehomingPostId;
    private Long userId;
    private String title;
    private String description;
    private String petName;
    private String petAge;
    private String category;
    private String type;
    private String gender;
    private String region;
    private int price;
    private Status status;
    private List<String> filePath;

    public RehomingResponseDto(Rehoming rehoming) {
    }
}
