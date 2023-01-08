package com.smile.petpat.post.trade.domain;

import com.smile.petpat.post.category.domain.PostType;
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
    private int viewCnt;
    private int bookmarkCnt;
    private int likeCnt;
    private boolean isBookmarked;
    private boolean isLiked;
    private String tradeCategoryDetailName;


    public TradeInfo(Long tradeId, Long userId, String title, String content, Long price, List<String> imageList, String location, PostType postType, int viewCnt,
                     int bookmarkCnt, int likeCnt, boolean isBookmarked, boolean isLiked, String tradeCategoryDetailName) {
        this.tradeId = tradeId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.price = price;
        this.imageList = imageList;
        this.location = location;
        this.postType = postType;
        this.viewCnt = viewCnt;
        this.bookmarkCnt = bookmarkCnt;
        this.likeCnt = likeCnt;
        this.isBookmarked = isBookmarked;
        this.isLiked = isLiked;
        this.tradeCategoryDetailName = tradeCategoryDetailName;
    }

    public TradeInfo(Trade trade, List<String> imageList, int bookmarkCnt,int likeCnt, boolean isBookmarked, boolean isLiked) {
        this.tradeId = trade.getTradeId();
        this.userId = trade.getUser().getId();
        this.title = trade.getTitle();
        this.content = trade.getContent();
        this.price = trade.getPrice();
        this.location = trade.getLocation();
        this.imageList = imageList;
        this.postType = trade.getPostType();
        this.viewCnt = trade.getViewCnt();
        this.bookmarkCnt = bookmarkCnt;
        this.likeCnt = likeCnt;
        this.isBookmarked = isBookmarked;
        this.isLiked = isLiked;
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
        this.viewCnt = trade.getViewCnt();
        this.tradeCategoryDetailName = trade.getTradeCategoryDetail().getTradeCategoryDetailName();
    }

    public TradeInfo(Trade trade, List<String> imgList) {
        this.tradeId = trade.getTradeId();
        this.userId = trade.getUser().getId();
        this.title = trade.getTitle();
        this.content = trade.getContent();
        this.price = trade.getPrice();
        this.imageList = imgList;
        this.location = trade.getLocation();
        this.postType = trade.getPostType();
        this.viewCnt = trade.getViewCnt();
    }
}
