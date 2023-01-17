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
    private boolean isLiked;
    private boolean isBookmarked;
    private int viewCnt;
    private Long likeCnt;
    private Long bookmarkCnt;
    private String tradeCategoryDetailName;


    public TradeInfo(Long tradeId, Long userId, String title, String content, Long price, List<String> imageList, String location, PostType postType,
                     boolean isBookmarked, boolean isLiked, int viewCnt, Long likeCnt, Long bookmarkCnt, String tradeCategoryDetailName) {
        this.tradeId = tradeId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.price = price;
        this.imageList = imageList;
        this.location = location;
        this.postType = postType;
        this.isLiked = isLiked;
        this.isBookmarked = isBookmarked;
        this.viewCnt = viewCnt;
        this.likeCnt = likeCnt;
        this.bookmarkCnt = bookmarkCnt;
        this.tradeCategoryDetailName = tradeCategoryDetailName;
    }

    public TradeInfo(Trade trade, List<String> imageList, boolean isLiked, boolean isBookmarked, Long likeCnt, Long bookmarkCnt) {
        this.tradeId = trade.getTradeId();
        this.userId = trade.getUser().getId();
        this.title = trade.getTitle();
        this.content = trade.getContent();
        this.price = trade.getPrice();
        this.location = trade.getLocation();
        this.imageList = imageList;
        this.postType = trade.getPostType();
        this.isLiked = isLiked;
        this.isBookmarked = isBookmarked;
        this.viewCnt = trade.getViewCnt();
        this.likeCnt = likeCnt;
        this.bookmarkCnt = bookmarkCnt;
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
