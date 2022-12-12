package com.smile.petpat.post.trade.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TradeInfo {

    private Long tradeId;
    private Long userId;
    private String title;
    private String content;
    private Long price;
    private String location;
    private PostType postType;
    private TradeCategoryDetail tradeCategoryDetail;

    public TradeInfo(Trade trade) {
        this.tradeId = trade.getTradeId();
        this.userId = trade.getUser().getId();
        this.title = trade.getTitle();
        this.content = trade.getContent();
        this.price = trade.getPrice();
        this.location = trade.getLocation();
        this.postType = trade.getPostType();
        this.tradeCategoryDetail = trade.getTradeCategoryDetail();
    }


}
