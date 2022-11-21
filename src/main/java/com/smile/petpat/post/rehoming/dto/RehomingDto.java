package com.smile.petpat.post.rehoming.dto;

//import com.smile.petpat.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RehomingDto {
//    private Long userId;
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
    private List<String> filePath;

    public RehomingDto(String title, String description, String petName, String petAge, String category, String type, String gender, String region, int price, boolean isCompleted, List<String> filePath) {
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
        this.isCompleted = isCompleted;
        this.filePath = filePath;
    }
}
