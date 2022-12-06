package com.smile.petpat.post.trade.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import lombok.Getter;

@Getter
public class TradeInfo {

    private String tradeId;
    private String title;
    private String content;
    private String price;
    private String location;
    private PostType postType;
    private TradeCategoryDetail tradeCategoryDetail;

    public TradeInfo(Trade trade) {
        this.tradeId = trade.getTradeId();
        this.title = trade.getTitle();
        this.content = trade.getContent();
        this.price = trade.getPrice();
        this.location = trade.getLocation();
        this.postType = trade.getPostType();
        this.tradeCategoryDetail = trade.getTradeCategoryDetail();
    }
}
