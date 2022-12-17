package com.smile.petpat.post.trade.domain;

import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import com.smile.petpat.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TradeCommand {

    private User user;
    private String title;
    private String content;
    private Long price;
    private String location;
    private TradeCategoryDetail tradeCategoryDetail;

    public TradeCommand(){

    }

    public TradeCommand(User user, String title, String content, Long price, String location, TradeCategoryDetail tradeCategoryDetail) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.price = price;
        this.location = location;
        this.tradeCategoryDetail = tradeCategoryDetail;
    }

    public Trade toRegisterEntity(User user){
        return Trade.builder()
                .user(user)
                .title(title)
                .content(content)
                .price(price)
                .location(location)
               // .tradeCategoryDetail(tradeCategoryDetail)
                .build();
    }

    public Trade toUpdateEntity(){
        return Trade.builder()
                .title(title)
                .content(content)
                .price(price)
                .location(location)
                // .tradeCategoryDetail(tradeCategoryDetail)
                .build();
    }
}
