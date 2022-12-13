package com.smile.petpat.post.rehoming.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RehomingReqDto {
    private String title;
    private String description;
    private String petName;
    private String petAge;
    private String category;
    private String type;
    private String gender;
    private String region;
    private int price;
    private List<String> filePath;
    private List<String> tagList;

    public RehomingReqDto(String title, String description, String petName, String petAge, String category, String type, String gender, String region, int price, List<String> filePath, List<String> tagList) {
        this.title = title;
        this.description = description;
        this.petName = petName;
        this.petAge = petAge;
        this.category = category;
        this.type = type;
        this.gender = gender;
        this.region = region;
        this.price = price;
        this.filePath = filePath;
        this.tagList = tagList;
    }

}
