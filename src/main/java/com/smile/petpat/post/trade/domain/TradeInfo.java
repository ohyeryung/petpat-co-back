package com.smile.petpat.post.trade.domain;

import com.smile.petpat.post.category.domain.PostGroup;
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
    private PostGroup postType;
    //private TradeCategoryDetail tradeCategoryDetail;

    public TradeInfo(Long tradeId, Long userId, String title, String content, Long price, String location, PostGroup postType
           // , TradeCategoryDetail tradeCategoryDetail
    ) {
        this.tradeId = tradeId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.price = price;
        this.location = location;
        this.postType = postType;
        //this.tradeCategoryDetail = tradeCategoryDetail;
    }

    public TradeInfo(Trade trade) {
        this.tradeId = trade.getTradeId();
        this.userId = trade.getUser().getId();
        this.title = trade.getTitle();
        this.content = trade.getContent();
        this.price = trade.getPrice();
        this.location = trade.getLocation();
        this.postType = trade.getPostType();
        //this.tradeCategoryDetail = trade.getTradeCategoryDetail();
    }


}
