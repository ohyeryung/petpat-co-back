package com.smile.petpat.post.trade.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
public class TradeDto {

    @Getter
    @ToString
    public static class RegisterTrade{

        @NotBlank(message = "제목은 필수값입니다.")
        private String title;
        @NotBlank(message = "내용은 필수값입니다.")
        private String content;
        @NotBlank(message = "가격은 필수값입니다.")
        private Long price;
        @NotBlank(message = "위치는 필수값입니다.")
        private String location;
        @NotBlank(message = "카테고리는 필수값입니다.")
        private TradeCategoryDetail tradeCategoryDetail;
        private List<MultipartFile>  images;

        public RegisterTrade(){

        }

        public RegisterTrade(String title, String content, Long price, String location, TradeCategoryDetail tradeCategoryDetail, List<MultipartFile> images) {
            if(images.size()>4) new IllegalArgumentException("중고거래 이미지는 5까지 등록가능합니다.");
            this.title = title;
            this.content = content;
            this.price = price;
            this.location = location;
            this.tradeCategoryDetail = tradeCategoryDetail;
            this.images = images;
        }

        public TradeCommand toCommand() {
         return TradeCommand.builder()
                 .title(title)
                 .content(content)
                 .price(price)
                 .location(location)
                 .tradeCategoryDetail(tradeCategoryDetail)
                 .build();

        }
    }
}
