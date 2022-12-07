package com.smile.petpat.post.trade.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Builder
@ToString
public class TradeCommand {

    private String title;
    private String content;
    private Long price;
    private String location;
    private TradeCategoryDetail tradeCategoryDetail;

    public TradeCommand(){

    }

    public TradeCommand(String title, String content, Long price, String location, TradeCategoryDetail tradeCategoryDetail) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.location = location;
        this.tradeCategoryDetail = tradeCategoryDetail;
    }
    public Trade toEntity(){
        return Trade.builder()
                .title(title)
                .content(content)
                .price(price)
                .location(location)
                .tradeCategoryDetail(tradeCategoryDetail)
                .build();
    }
}
