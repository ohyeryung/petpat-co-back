package com.smile.petpat.post.rehoming.domain;

import com.smile.petpat.post.category.domain.CategoryGroup;
import com.smile.petpat.post.category.domain.PetCategory;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.status.PostStatus;
import com.smile.petpat.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class RehomingCommand {

    private User user;
    private  String title;
    private String description;
    private int viewCnt;
    private String petName;
    private String petAge;
    private Long category;
    private Long type;
    private String gender;
    private String location;
    private Long price;

    private PetCategory petCategory;

    private PostStatus status;

    public Rehoming toRegisterEntity(User user, CategoryGroup category, PetCategory type) {
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
                .location(location)
                .price(price)
                .status(PostStatus.REHOMING_FINDING)
                .postType(PostType.REHOMING)
                .build();
    }

    public Rehoming toUpdateEntity(User user, Long rehomingId, CategoryGroup category, PetCategory type) {
        return Rehoming.builder()
                .rehomingId(rehomingId)
                .user(user)
                .title(title)
                .description(description)
                .petName(petName)
                .petAge(petAge)
                .category(category)
                .type(type)
                .gender(gender)
                .location(location)
                .price(price)
                .status(PostStatus.REHOMING_FINDING)
                .postType(PostType.REHOMING)
                .build();
    }
}
