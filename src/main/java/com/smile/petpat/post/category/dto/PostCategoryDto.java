package com.smile.petpat.post.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
public class PostCategoryDto {

    @Getter
    @Setter
    @ToString
    public static class RequestRegisterCategoryGroup{
        private String categoryGroupName;
        private String postType;
    }

    @Getter
    @AllArgsConstructor
    public static class TradeCategoryResponse{
        private Long tradeCategoryId;
        private String tradeCategoryName;
        List<TradeCategoryDetailResponse> tradeCategoryDetailList;
    }

    @Getter
    @AllArgsConstructor
    public static class TradeCategoryDetailResponse{
        private Long tradeCategoryDetailId;
        private String tradeCategoryDetailName;
        private Long tradeCategoryDetailCnt;
    }

    @Getter
    @AllArgsConstructor
    public static class RehomingCategoryResponse{
        private Long petCategoryId;
        private String petCategoryName;
        private Long petCategoryCnt;
    }

    @Getter
    @AllArgsConstructor
    public static class RehomingCategoryList {
        private Long categoryGroupId;
        private String categoryGroupName;
        private Long petCategoryId;
        private String petCategoryName;
    }

    @Getter
    @AllArgsConstructor
    public static class TradeCategoryList {
        private Long categoryGroupId;
        private String categoryGroupName;
        private List<TradeCategoryResponse> tradeCategoryResponseList;
    }

}
