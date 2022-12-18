package com.smile.petpat.post.rehoming.dto;

import com.smile.petpat.post.rehoming.domain.RehomingCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
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
    private Long price;
    private List<String> filePath;
    private List<String> tagList;

    public RehomingReqDto(String title, String description, String petName, String petAge, String category, String type, String gender, String region, Long price, List<String> filePath, List<String> tagList) {
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
    public RehomingCommand toCommand() {
        return RehomingCommand.builder()
                .title(title)
                .description(description)
                .petName(petName)
                .petAge(petAge)
                .category(category)
                .type(type)
                .gender(gender)
                .region(region)
                .price(price)
                .build();
    }

}
