package com.smile.petpat.post.rehoming.domain;

import com.smile.petpat.post.category.domain.PetCategory;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RehomingCommand {

    private User user;
    private  String title;
    private String description;
    private int viewCnt;
    private String petName;
    private String petAge;
    private String category;
    private String type;
    private String gender;
    private String region;
    private Long price;

//    private List<String> tagList;
    private PetCategory petCategory;

    public Rehoming toRegisterEntity(User user) {
        return Rehoming.builder()
                .user(user)
                .title(title)
                .description(description)
                .viewCnt(viewCnt)
                .petName(petName)
                .petAge(petAge)
                .category(category)
                .type(type)
                .gender(gender)
                .region(region)
                .price(price)
                .status(Status.FINDING)
                .postType(PostType.REHOMING)
                .build();
    }
}
