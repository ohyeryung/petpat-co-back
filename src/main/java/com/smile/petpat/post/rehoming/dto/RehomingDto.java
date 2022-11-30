package com.smile.petpat.post.rehoming.dto;

import com.smile.petpat.post.rehoming.domain.Rehoming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RehomingDto {
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

    public RehomingDto(String title, String description, String petName, String petAge, String category, String type, String gender, String region, int price, List<String> filePath) {
//        this.userId = userId;
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
    }

    public RehomingDto(Rehoming rehoming) {
        this.title = rehoming.getTitle();
        this.description = rehoming.getDescription();
        this.petName = rehoming.getPetName();
        this.petAge = rehoming.getPetAge();
        this.category = rehoming.getCategory();
        this.type = rehoming.getType();
        this.gender = rehoming.getGender();
        this.region = rehoming.getRegion();
        this.price = rehoming.getPrice();
    }
}
