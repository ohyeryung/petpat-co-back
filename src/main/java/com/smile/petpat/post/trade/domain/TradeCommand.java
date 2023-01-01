package com.smile.petpat.post.trade.domain;

import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import com.smile.petpat.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Builder
@ToString
public class TradeCommand {
    private User user;
    private String title;
    private String content;
    private Long price;
    private String location;
    private Long tradeCategoryDetailId;
    private List<MultipartFile> images;

    public TradeCommand(){

    }

    public TradeCommand(User user, String title, String content, Long price, String location, Long tradeCategoryDetailId, List<MultipartFile> images) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.price = price;
        this.location = location;
        this.tradeCategoryDetailId = tradeCategoryDetailId;
        this.images = images;
    }

    public Trade toRegisterEntity(User user, TradeCategoryDetail tradeCategoryDetail){
        return Trade.builder()
                .user(user)
                .title(title)
                .content(content)
                .price(price)
                .location(location)
                .tradeCategoryDetail(tradeCategoryDetail)
                .build();
    }

    public Trade toUpdateEntity(User user,Long tradeId,TradeCategoryDetail tradeCategoryDetail){
        return Trade.builder()
                .tradeId(tradeId)
                .user(user)
                .title(title)
                .content(content)
                .price(price)
                .location(location)
                .tradeCategoryDetail(tradeCategoryDetail)
                .build();
    }
}
