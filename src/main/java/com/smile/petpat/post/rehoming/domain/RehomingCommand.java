package com.smile.petpat.post.rehoming.domain;

import com.smile.petpat.post.category.domain.CategoryGroup;
import com.smile.petpat.post.category.domain.PetCategory;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.status.PostStatus;
import com.smile.petpat.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RehomingCommand {

    private User user;
    private String title;
    private String description;
    private String petName;
    private String petAge;
    private Long category;
    private Long type;
    private String gender;
    private String cityName;
    private String cityCountryName;
    private String townShipName;
    private String detailAdName;
    private String fullAdName;
    private Long price;
    private int viewCnt;

    public RehomingCommand toCommand() {
        return RehomingCommand.builder()
                .title(title)
                .description(description)
                .petName(petName)
                .petAge(petAge)
                .category(category)
                .type(type)
                .gender(gender)
                .cityName(cityName)
                .cityCountryName(cityCountryName)
                .townShipName(townShipName)
                .detailAdName(detailAdName)
                .fullAdName(fullAdName)
                .price(price)
                .build();
    }

    public Rehoming toRegisterEntity(User user, CategoryGroup category, PetCategory type) {
        return Rehoming.builder()
                .user(user)
                .title(title)
                .description(description)
                .petName(petName)
                .petAge(petAge)
                .category(category)
                .type(type)
                .gender(gender)
                .cityName(cityName)
                .cityCountryName(cityCountryName)
                .townShipName(townShipName)
                .detailAdName(detailAdName)
                .fullAdName(fullAdName)
                .price(price)
                .viewCnt(viewCnt)
                .status(PostStatus.REHOMING_FINDING)
                .postType(PostType.REHOMING)
                .build();
    }

    public Rehoming toUpdateEntity(User user, Long rehomingId, CategoryGroup category, PetCategory type, PostStatus status) {
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
                .cityName(cityName)
                .cityCountryName(cityCountryName)
                .townShipName(townShipName)
                .detailAdName(detailAdName)
                .fullAdName(fullAdName)
                .price(price)
                .status(status)
                .build();
    }
}
