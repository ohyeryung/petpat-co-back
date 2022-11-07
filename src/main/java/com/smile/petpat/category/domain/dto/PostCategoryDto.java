package com.smile.petpat.category.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
public class PostCategoryDto {

    @Getter
    @Setter
    @ToString
    public static class RequestRegisterCategoryGroup{

        private String categoryGroupName;
        private String postType;





    }
}
