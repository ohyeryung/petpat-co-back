package com.smile.petpat.post.trade.domain;

import com.smile.petpat.image.domain.Image;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TradeInfo {

    private Long tradeId;
    private Long userId;
    private String title;
    private String content;
    private Long price;
    private List<String> imageList;
    private String location;
    private PostType postType;
    private String tradeCategoryDetailName;


    public TradeInfo(Long tradeId, Long userId, String title, String content, Long price, List<String> imageList, String location, PostType postType, String tradeCategoryDetailName) {
        this.tradeId = tradeId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.price = price;
        this.imageList = imageList;
        this.location = location;
        this.postType = postType;
        this.tradeCategoryDetailName = tradeCategoryDetailName;
    }

    public TradeInfo(Trade trade, List<String> imageList) {
        this.tradeId = trade.getTradeId();
        this.userId = trade.getUser().getId();
        this.title = trade.getTitle();
        this.content = trade.getContent();
        this.price = trade.getPrice();
        this.location = trade.getLocation();
        this.imageList = imageList;
        this.postType = trade.getPostType();
        this.tradeCategoryDetailName = trade.getTradeCategoryDetail().getTradeCategoryDetailName();
    }

    public TradeInfo(Trade trade) {
        this.tradeId = trade.getTradeId();
        this.userId = trade.getUser().getId();
        this.title = trade.getTitle();
        this.content = trade.getContent();
        this.price = trade.getPrice();
        this.location = trade.getLocation();
        this.postType = trade.getPostType();
        this.tradeCategoryDetailName = trade.getTradeCategoryDetail().getTradeCategoryDetailName();
    }


}
