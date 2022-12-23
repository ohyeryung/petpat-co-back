package com.smile.petpat.post.category.dto;

import com.smile.petpat.post.category.domain.PetCategory;
import com.smile.petpat.post.category.domain.TradeCategory;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class PostCategoryInfo {

    public static class CategoryGroupRes{

    }

    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PetCategoryRes {
        private Long petCategoryId;
        private String petCategoryName;

        public PetCategoryRes(PetCategory petCategory) {
            this.petCategoryId = petCategory.getPetCategoryId();
            this.petCategoryName = petCategory.getPostCategoryName();
        }
    }

    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TradeCategoryRes{
        private Long tradeCategoryId;
        private String tradeCategoryName;

        public TradeCategoryRes(TradeCategory tradeCategory){
            this.tradeCategoryId = tradeCategory.getTradeCategoryId();
            this.tradeCategoryName = tradeCategory.getTradeCategoryName();
        }

    }

    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TradeCategoryDetailRes{
        private Long tradeCategoryDetailId;
        private String tradeCategoryDetailName;

        public TradeCategoryDetailRes(TradeCategoryDetail tradeCategoryDetail){
            this.tradeCategoryDetailId = tradeCategoryDetail.getTradeCategoryDetailId();
            this.tradeCategoryDetailName = tradeCategoryDetail.getTradeCategoryName();
        }
    }

}
