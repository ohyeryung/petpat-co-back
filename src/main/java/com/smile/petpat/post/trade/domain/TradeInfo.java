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
    private String cityName;
    private String cityCountryName;
    private String townShipName;
    private String detailAdName;
    private String fullAdName;

    private List<String> imageList;
    private PostType postType;
    private boolean isLiked;
    private boolean isBookmarked;
    private int viewCnt;
    private Long likeCnt;
    private Long bookmarkCnt;
    private String tradeCategoryDetailName;


    public TradeInfo(Long tradeId, Long userId, String title, String content, Long price, String cityName, String cityCountryName, String townShipName, String detailAdName, String fullAdName, List<String> imageList, PostType postType, boolean isLiked, boolean isBookmarked, int viewCnt, Long likeCnt, Long bookmarkCnt, String tradeCategoryDetailName) {
        this.tradeId = tradeId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.price = price;
        this.cityName = cityName;
        this.cityCountryName = cityCountryName;
        this.townShipName = townShipName;
        this.detailAdName = detailAdName;
        this.fullAdName = fullAdName;
        this.imageList = imageList;
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
        this.cityName = trade.getCityName();
        this.cityCountryName = trade.getCityCountryName();
        this.townShipName = trade.getTownShipName();
        this.detailAdName = trade.getDetailAdName();
        this.fullAdName = trade.getFullAdName();
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
        this.cityName = trade.getCityName();
        this.cityCountryName = trade.getCityCountryName();
        this.townShipName = trade.getTownShipName();
        this.detailAdName = trade.getDetailAdName();
        this.fullAdName = trade.getFullAdName();
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
        this.cityName = trade.getCityName();
        this.cityCountryName = trade.getCityCountryName();
        this.townShipName = trade.getTownShipName();
        this.detailAdName = trade.getDetailAdName();
        this.fullAdName = trade.getFullAdName();
        this.postType = trade.getPostType();
        this.viewCnt = trade.getViewCnt();
    }
}
