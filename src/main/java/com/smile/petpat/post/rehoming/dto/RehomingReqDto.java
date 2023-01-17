package com.smile.petpat.post.rehoming.dto;

import com.smile.petpat.post.category.domain.CategoryGroup;
import com.smile.petpat.post.category.domain.PetCategory;
import com.smile.petpat.post.rehoming.domain.RehomingCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class RehomingReqDto {
    private String title;
    private String description;
    private String petName;
    private String petAge;
    private Long category;
    private Long type;
    private String gender;
    private String location;
    private Long price;
    private List<String> filePath;

    public RehomingReqDto(String title, String description, String petName, String petAge, Long category, Long type, String gender, String location, Long price, List<String> filePath) {
        this.title = title;
        this.description = description;
        this.petName = petName;
        this.petAge = petAge;
        this.category = category;
        this.type = type;
        this.gender = gender;
        this.location = location;
        this.price = price;
        this.filePath = filePath;
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
                .location(location)
                .price(price)
                .build();
    }

}
